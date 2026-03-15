package io.github.fornewid.core.kotlin

import android.content.Context

interface AppGraphProvider {
    val appGraph: Any
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Context.appGraph(): T {
    return (applicationContext as AppGraphProvider).appGraph as T
}
