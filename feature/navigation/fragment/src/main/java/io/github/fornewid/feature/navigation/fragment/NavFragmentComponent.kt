package io.github.fornewid.feature.navigation.fragment

import dev.zacsweers.metro.ContributesTo
import io.github.fornewid.core.metro.AppScope

@ContributesTo(AppScope::class)
interface NavFragmentComponent {
    fun navFragmentViewModel(): ExampleNavGraphViewModel
}
