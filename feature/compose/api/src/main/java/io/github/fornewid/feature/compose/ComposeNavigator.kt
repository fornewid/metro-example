package io.github.fornewid.feature.compose

import android.content.Context
import android.content.Intent

interface ComposeNavigator {
    fun createIntent(context: Context): Intent
}
