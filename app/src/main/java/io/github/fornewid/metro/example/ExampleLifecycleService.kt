package io.github.fornewid.metro.example

import android.content.Intent
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import io.github.fornewid.core.metro.appGraph
import io.github.fornewid.feature.bar.Bar
import kotlinx.coroutines.launch

class ExampleLifecycleService : LifecycleService() {

    private lateinit var bar: Bar

    override fun onCreate() {
        super.onCreate()
        bar = appGraph<AppGraph>().bar
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bar.toString()
        lifecycleScope.launch {
            /* do something */
        }
        return super.onStartCommand(intent, flags, startId)
    }
}
