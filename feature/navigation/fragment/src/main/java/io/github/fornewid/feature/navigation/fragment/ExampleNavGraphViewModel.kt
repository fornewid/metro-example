package io.github.fornewid.feature.navigation.fragment

import androidx.lifecycle.ViewModel
import dev.zacsweers.metro.Inject
import io.github.fornewid.feature.bar.Bar

class ExampleNavGraphViewModel @Inject constructor(
    private val bar: Bar,
) : ViewModel() {

    fun doSomething() {
        bar.toString()
    }
}
