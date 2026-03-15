package io.github.fornewid.feature.bar.impl

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.core.kotlin.InternalFeatureApi
import io.github.fornewid.feature.bar.Bar
import io.github.fornewid.feature.foo.Foo

@InternalFeatureApi
@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
@Inject
class BarImpl(
    private val foo: Foo,
) : Bar {

    override fun toString(): String {
        return "BarImpl(foo=$foo)"
    }
}
