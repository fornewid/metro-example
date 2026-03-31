package io.github.fornewid.feature.work.impl

import android.app.Application
import androidx.work.WorkManager
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.github.fornewid.core.metro.AppScope
import io.github.fornewid.core.metro.InternalFeatureApi

@InternalFeatureApi
@ContributesTo(AppScope::class)
interface WorkModule {

    @SingleIn(AppScope::class)
    @Provides
    fun providesWorkManager(
        application: Application,
    ): WorkManager {
        return WorkManager.getInstance(application)
    }
}
