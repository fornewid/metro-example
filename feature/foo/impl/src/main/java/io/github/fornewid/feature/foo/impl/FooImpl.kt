package io.github.fornewid.feature.foo.impl

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.core.kotlin.InternalFeatureApi
import io.github.fornewid.feature.foo.Foo

@InternalFeatureApi
@ContributesBinding(AppScope::class)
@Inject
class FooImpl : Foo {

    override fun toString(): String {
        return "FooImpl@" + hashCode().toString(16)
    }
}
