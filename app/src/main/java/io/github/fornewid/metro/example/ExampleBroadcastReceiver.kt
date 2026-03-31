package io.github.fornewid.metro.example

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.github.fornewid.core.metro.appGraph
import io.github.fornewid.feature.bar.Bar

class ExampleBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val bar: Bar = context.appGraph<AppGraph>().bar
        bar.toString()
    }
}
