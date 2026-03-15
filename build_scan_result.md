# Build Scan Result: Metro 단일 모듈 빌드 성능 측정

## 실험 개요

Metro 단일 모듈 구조에서 코드 변경 유형별 incremental 빌드 성능을 측정합니다.
3가지 코드 변경 시나리오를 각각 30회 반복 측정하였습니다.

- **측정 방식**: `./gradlew :app:assembleDebug` incremental 빌드 시간 (python3 ms 단위)
- **반복 횟수**: 시나리오별 30회 (15 toggle cycle × 2)
- **실행 조건**: 순차 실행, Gradle Daemon warm 상태
- **통계**: 워밍업 2회 제외 후 outlier(>15s) 필터링

---

## 1. 모듈 구조

### [4] Metro 단일 모듈 — 현재 구조

```
┌──────────────────────────────────────┐
│ feature:foo                          │
│ ├─ api (Interface)                   │
│ ├─ impl (Logic/UI)                   │
│ └─ DI (@ContributesBinding)          │
│                                      │
│ 🚀 KSP 없음 (컴파일러 플러그인 직결) │
└──────────────────────────────────────┘
  🔻 의존: :app 모듈 (KSP 없는 단일 빌드)
```

Metro는 KSP(코드 생성)가 아닌 **컴파일러 플러그인**으로 동작하므로, 별도의 KSP task가 존재하지 않습니다.
DI 바인딩 생성이 `compileDebugKotlin` task 내에서 처리됩니다.

---

## 2. 테스트 시나리오

| Test Case | 설명 | 수정 대상 |
|-----------|------|----------|
| **A** | 내부 구현 로직 수정 (Non-ABI) | `FooImpl.kt`에 private 메서드 추가 |
| **B** | 인터페이스 수정 (ABI 변경) | `Foo` interface에 메서드 추가 + `FooImpl` 구현 |
| **C** | 새로운 의존성 바인딩 추가 | `NewService` interface + `NewServiceImpl` + `@ContributesBinding` |

---

## 3. 측정 결과 (30회 반복, 순차 실행)

### Test Case A: 내부 구현 로직 수정 (Non-ABI)

> 가장 빈번한 일상 개발 시나리오 — impl 내부 로직만 수정

| 지표 | Metro 단일 모듈 |
|------|----------------|
| **중앙값** | **2,798 ms** |
| 평균 | 2,882 ms |
| P90 | 3,280 ms |
| 최솟값 | 2,297 ms |
| 실행 Task | **9 / 249** |
| KSP 실행 | **0개** |

### Test Case B: 인터페이스 수정 (ABI 변경)

> 인터페이스 변경으로 의존 모듈이 연쇄 재컴파일되는 시나리오

| 지표 | Metro 단일 모듈 |
|------|----------------|
| **중앙값** | **2,438 ms** |
| 평균 | 2,514 ms |
| P90 | 3,042 ms |
| 최솟값 | 2,177 ms |
| 실행 Task | **9 / 249** |
| KSP 실행 | **0개** |

### Test Case C: 새로운 의존성 바인딩 추가

> 새 interface + impl + `@ContributesBinding` 추가/제거를 토글하는 시나리오

| 지표 | Metro 단일 모듈 |
|------|----------------|
| **중앙값** | **3,592 ms** |
| 평균 | 3,813 ms |
| P90 | 5,095 ms |
| 최솟값 | 2,841 ms |
| 실행 Task | **12 / 249** |
| KSP 실행 | **0개** |

---

## 4. 실행 Task 상세

### Test Case A: 내부 구현 로직 수정 (Non-ABI)

| # | Task | 상태 |
|---|------|------|
| 1 | `:feature:foo:compileDebugKotlin` | EXECUTED |
| 2 | `:feature:foo:bundleLibRuntimeToJarDebug` | EXECUTED |
| 3 | `:feature:foo:bundleLibCompileToJarDebug` | EXECUTED |
| 4 | `:feature:bar:compileDebugKotlin` | EXECUTED |
| 5 | `:app:compileDebugKotlin` | EXECUTED |
| 6 | `:app:dexBuilderDebug` | EXECUTED |
| 7 | `:app:mergeLibDexDebug` | EXECUTED |
| 8 | `:app:packageDebug` | EXECUTED |
| 9 | `:app:assembleDebug` | EXECUTED |
| — | 그 외 240개 task | UP-TO-DATE |

### Test Case B: 인터페이스 수정 (ABI 변경)

| # | Task | 상태 |
|---|------|------|
| 1 | `:feature:foo:compileDebugKotlin` | EXECUTED |
| 2 | `:feature:foo:bundleLibRuntimeToJarDebug` | EXECUTED |
| 3 | `:feature:foo:bundleLibCompileToJarDebug` | EXECUTED |
| 4 | `:feature:bar:compileDebugKotlin` | EXECUTED |
| 5 | `:app:compileDebugKotlin` | EXECUTED |
| 6 | `:app:dexBuilderDebug` | EXECUTED |
| 7 | `:app:mergeLibDexDebug` | EXECUTED |
| 8 | `:app:packageDebug` | EXECUTED |
| 9 | `:app:assembleDebug` | EXECUTED |
| — | 그 외 240개 task | UP-TO-DATE |

### Test Case C: 새로운 의존성 바인딩 추가

| # | Task | 상태 |
|---|------|------|
| 1 | `:feature:foo:compileDebugKotlin` | EXECUTED |
| 2 | `:feature:foo:processDebugJavaRes` | EXECUTED |
| 3 | `:feature:foo:bundleLibRuntimeToJarDebug` | EXECUTED |
| 4 | `:feature:foo:bundleLibCompileToJarDebug` | EXECUTED |
| 5 | `:feature:bar:compileDebugKotlin` | EXECUTED |
| 6 | `:app:compileDebugKotlin` | EXECUTED |
| 7 | `:app:dexBuilderDebug` | EXECUTED |
| 8 | `:app:mergeLibDexDebug` | EXECUTED |
| 9 | `:app:mergeProjectDexDebug` | EXECUTED |
| 10 | `:app:mergeDebugJavaResource` | EXECUTED |
| 11 | `:app:packageDebug` | EXECUTED |
| 12 | `:app:assembleDebug` | EXECUTED |
| — | 그 외 237개 task | UP-TO-DATE |

---

## 5. 분석

### 핵심 발견: 모든 시나리오에서 KSP 0개

| 시나리오 | 중앙값 | 실행 Task | KSP |
|----------|--------|-----------|-----|
| A. impl 수정 (Non-ABI) | 2,798 ms | 9 / 249 | **0개** |
| B. API 수정 (ABI 변경) | 2,438 ms | 9 / 249 | **0개** |
| C. 새 바인딩 추가 | 3,592 ms | 12 / 249 | **0개** |

- **KSP task가 0개**: Metro는 컴파일러 플러그인으로 동작하므로, 별도의 KSP annotation processing 과정이 없습니다. DI 코드 생성이 `compileDebugKotlin` 내에서 처리됩니다.
- **Test A/B 실행 Task 수 동일 (9개)**: impl 수정이든 API 수정이든 동일한 task 체인이 실행됩니다. Dagger/Hilt에서는 ABI 변경 시 KSP가 연쇄 실행되어 task 수가 크게 증가하지만, Metro에서는 차이가 없습니다.
- **Test C는 약간 더 많은 task (12개)**: 새 파일 추가/삭제로 인해 `processDebugJavaRes`, `mergeProjectDexDebug`, `mergeDebugJavaResource` 등 추가 task가 실행되지만, KSP task는 여전히 0개입니다.

### Dagger/Hilt 대비 비교 (참고)

> [dagger-hilt-example](https://github.com/fornewid/dagger-hilt-example) 프로젝트의 동일 시나리오 측정 결과와 비교

| 시나리오 | Hilt 단일 모듈 | Hilt api/impl | Hilt 3모듈 | **Metro 단일** |
|----------|---------------|---------------|------------|---------------|
| A. impl 수정 | 5,806 ms | 5,659 ms | 4,842 ms | **2,798 ms** |
| B. API 수정 | 7,332 ms | 5,879 ms | 6,419 ms | **2,438 ms** |
| C. 새 바인딩 추가 | 6,593 ms | 5,750 ms | 6,166 ms | **3,592 ms** |
| KSP task (A) | 1개 | 1개 | 0개 | **0개** |
| KSP task (B/C) | 3개 | 3개 | 3개 | **0개** |

- Metro 단일 모듈이 Hilt 3모듈 최적화 구조(bindings 분리)보다도 **약 42% 빠름** (Test A 기준)
- Hilt에서 ABI 변경 시 빌드 시간이 증가하는 반면(5,806→7,332ms), Metro는 **거의 동일** (2,798→2,438ms)
- 새 바인딩 추가(Test C)에서도 Metro는 Hilt 최적 구조 대비 **약 42% 빠름** (3,592 vs 6,166ms)

### Task 실행 패턴 비교

```
Hilt 단일:     foo:ksp → foo:compile → app:ksp → app:compile → package (KSP 실행)
Hilt api/impl: foo:impl:ksp → foo:impl:compile → app:ksp → package (KSP 실행)
Hilt 3모듈:    foo:impl:compile → foo:bindings:compile (KSP 스킵) → package (KSP 0개, Test A만)
Metro 단일:    foo:compile → bar:compile → app:compile → package (KSP 자체가 없음, 모든 시나리오)
```

### 결론

Metro의 컴파일러 플러그인 방식은 KSP 기반 DI 대비 근본적인 빌드 성능 우위를 가집니다:
1. **KSP task 완전 제거** — annotation processing 오버헤드 없음
2. **코드 변경 유형에 무관한 일정한 빌드 시간** — API 변경이든 impl 변경이든 유사
3. **모듈 분리 없이 최적 성능** — Hilt의 3모듈 분리 최적화보다 더 빠른 빌드를 단일 모듈로 달성

---

## 6. 전체 측정 데이터 (Raw)

<details>
<summary>Test Case A (30회)</summary>

| # | ms | # | ms | # | ms |
|---|-----|---|-----|---|-----|
| 1 | 5,703 | 11 | 2,759 | 21 | 2,855 |
| 2 | 4,150 | 12 | 3,385 | 22 | 2,411 |
| 3 | 3,280 | 13 | 2,808 | 23 | 2,677 |
| 4 | 2,859 | 14 | 2,891 | 24 | 2,708 |
| 5 | 2,545 | 15 | 2,766 | 25 | 2,960 |
| 6 | 2,995 | 16 | 2,686 | 26 | 3,007 |
| 7 | 2,962 | 17 | 2,603 | 27 | 2,957 |
| 8 | 2,822 | 18 | 2,769 | 28 | 3,216 |
| 9 | 4,721 | 19 | 2,771 | 29 | 2,297 |
| 10 | 2,769 | 20 | 2,447 | 30 | 2,788 |

</details>

<details>
<summary>Test Case B (30회)</summary>

| # | ms | # | ms | # | ms |
|---|-----|---|-----|---|-----|
| 1 | 2,567 | 11 | 3,082 | 21 | 2,419 |
| 2 | 2,870 | 12 | 2,522 | 22 | 2,356 |
| 3 | 2,729 | 13 | 2,177 | 23 | 2,279 |
| 4 | 2,483 | 14 | 3,271 | 24 | 2,499 |
| 5 | 2,459 | 15 | 2,877 | 25 | 2,652 |
| 6 | 2,225 | 16 | 2,199 | 26 | 2,320 |
| 7 | 2,850 | 17 | 2,304 | 27 | 2,456 |
| 8 | 2,382 | 18 | 2,303 | 28 | 2,417 |
| 9 | 2,307 | 19 | 2,190 | 29 | 3,042 |
| 10 | 2,196 | 20 | 2,709 | 30 | 2,700 |

</details>

<details>
<summary>Test Case C (30회)</summary>

| # | ms | # | ms | # | ms |
|---|-----|---|-----|---|-----|
| 1 | 7,254 | 11 | 4,177 | 21 | 3,738 |
| 2 | 4,612 | 12 | 3,470 | 22 | 3,060 |
| 3 | 4,148 | 13 | 4,895 | 23 | 3,428 |
| 4 | 3,695 | 14 | 3,318 | 24 | 4,041 |
| 5 | 3,815 | 15 | 3,461 | 25 | 4,085 |
| 6 | 3,028 | 16 | 3,440 | 26 | 3,031 |
| 7 | 5,143 | 17 | 3,490 | 27 | 5,610 |
| 8 | 3,438 | 18 | 2,879 | 28 | 3,375 |
| 9 | 4,150 | 19 | 4,030 | 29 | 4,411 |
| 10 | 3,483 | 20 | 5,095 | 30 | 2,841 |

</details>
