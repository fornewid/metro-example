package io.github.fornewid.feature.work.impl

import dev.zacsweers.metro.ContributesTo
import io.github.fornewid.core.kotlin.AppScope

@ContributesTo(AppScope::class)
interface WorkerComponent {
    fun exampleUseCase(): ExampleUseCase
}
