package io.github.fornewid.metro.example

import androidx.lifecycle.ViewModel
import dev.zacsweers.metro.Inject
import io.github.fornewid.feature.bar.Bar

@Inject
class ExampleViewModel(
    private val bar: Bar,
) : ViewModel() {

    fun doSomething() {
        bar.toString()
    }
}
