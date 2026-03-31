package io.github.fornewid.feature.foo.impl

import android.content.Context
import android.content.Intent
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.fornewid.core.metro.AppScope
import io.github.fornewid.core.metro.InternalFeatureApi
import io.github.fornewid.feature.foo.FooNavigator

@InternalFeatureApi
@ContributesBinding(AppScope::class)
@Inject
class FooNavigatorImpl : FooNavigator {
    override fun createIntent(context: Context): Intent {
        return Intent(context, FooActivity::class.java)
    }
}
