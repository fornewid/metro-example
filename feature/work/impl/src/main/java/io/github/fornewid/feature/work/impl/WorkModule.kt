package io.github.fornewid.feature.work.impl

import android.app.Application
import androidx.work.WorkManager
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.core.kotlin.InternalFeatureApi

@InternalFeatureApi
@ContributesTo(AppScope::class)
internal interface WorkModule {

    @SingleIn(AppScope::class)
    @Provides
    fun providesWorkManager(
        application: Application,
    ): WorkManager {
        return WorkManager.getInstance(application)
    }
}
