package io.github.fornewid.feature.bar.impl

import android.content.Context
import android.content.Intent
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.core.kotlin.InternalFeatureApi
import io.github.fornewid.feature.bar.BarNavigator

@InternalFeatureApi
@ContributesBinding(AppScope::class)
@Inject
class BarNavigatorImpl : BarNavigator {
    override fun createIntent(context: Context): Intent {
        return Intent(context, BarActivity::class.java)
    }
}
