package io.github.fornewid.feature.compose.impl

import android.content.Context
import android.content.Intent
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.core.kotlin.InternalFeatureApi
import io.github.fornewid.feature.compose.ComposeNavigator
import io.github.fornewid.feature.compose.ExampleComposeActivity

@InternalFeatureApi
@ContributesBinding(AppScope::class)
@Inject
internal class ComposeNavigatorImpl : ComposeNavigator {
    override fun createIntent(context: Context): Intent {
        return Intent(context, ExampleComposeActivity::class.java)
    }
}
