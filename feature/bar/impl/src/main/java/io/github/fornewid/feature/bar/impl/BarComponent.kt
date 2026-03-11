package io.github.fornewid.feature.bar.impl

import dev.zacsweers.metro.ContributesTo
import io.github.fornewid.core.kotlin.AppScope

@ContributesTo(AppScope::class)
interface BarComponent {
    fun barViewModel(): BarViewModel
}
