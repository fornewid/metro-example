package io.github.fornewid.metro.example

import android.app.Application
import dev.zacsweers.metro.createGraphFactory
import io.github.fornewid.core.kotlin.AppGraphProvider

class ExampleApplication : Application(), AppGraphProvider {

    override val appGraph: Any by lazy {
        createGraphFactory<AppGraph.Factory>().create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}
