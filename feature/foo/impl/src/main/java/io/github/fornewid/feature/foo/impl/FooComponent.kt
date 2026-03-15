package io.github.fornewid.feature.foo.impl

import dev.zacsweers.metro.ContributesTo
import io.github.fornewid.core.kotlin.AppScope

@ContributesTo(AppScope::class)
interface FooComponent {
    fun fooViewModel(): FooViewModel
}
