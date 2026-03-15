package io.github.fornewid.metro.example

import android.app.Application
import androidx.work.Configuration
import dev.zacsweers.metro.createGraphFactory
import io.github.fornewid.core.kotlin.AppGraphProvider

class ExampleApplication : Application(), AppGraphProvider, Configuration.Provider {

    override val appGraph: Any by lazy {
        createGraphFactory<AppGraph.Factory>().create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory((appGraph as AppGraph).workerFactory)
            .build()
}
