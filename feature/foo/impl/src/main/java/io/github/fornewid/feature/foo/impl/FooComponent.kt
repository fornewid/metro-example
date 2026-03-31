package io.github.fornewid.feature.foo.impl

import dev.zacsweers.metro.ContributesTo
import io.github.fornewid.core.metro.AppScope
import io.github.fornewid.core.metro.InternalFeatureApi

@InternalFeatureApi
@ContributesTo(AppScope::class)
interface FooComponent {
    fun fooViewModel(): FooViewModel
}
