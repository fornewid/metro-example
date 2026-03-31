package io.github.fornewid.feature.bar.impl

import dev.zacsweers.metro.ContributesTo
import io.github.fornewid.core.metro.AppScope
import io.github.fornewid.core.metro.InternalFeatureApi

@InternalFeatureApi
@ContributesTo(AppScope::class)
interface BarComponent {
    fun barViewModel(): BarViewModel
}
