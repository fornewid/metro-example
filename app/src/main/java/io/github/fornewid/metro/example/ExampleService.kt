package io.github.fornewid.metro.example

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.github.fornewid.core.kotlin.appGraph
import io.github.fornewid.feature.bar.Bar

class ExampleService : Service() {

    private lateinit var bar: Bar

    override fun onCreate() {
        super.onCreate()
        bar = appGraph<AppGraph>().bar
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bar.toString()
        return super.onStartCommand(intent, flags, startId)
    }
}
