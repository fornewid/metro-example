package io.github.fornewid.feature.navigation.compose

import dev.zacsweers.metro.ContributesTo
import io.github.fornewid.core.kotlin.AppScope

@ContributesTo(AppScope::class)
interface NavComposeComponent {
    fun navComposeViewModel(): ExampleNavGraphViewModel
}
