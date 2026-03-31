package io.github.fornewid.feature.compose

import dev.zacsweers.metro.ContributesTo
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.feature.bar.Bar

@ContributesTo(AppScope::class)
interface ComposeComponent {
    val bar: Bar
}
