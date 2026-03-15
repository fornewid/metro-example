package io.github.fornewid.feature.navigation.compose

import androidx.lifecycle.ViewModel
import dev.zacsweers.metro.Inject
import io.github.fornewid.feature.bar.Bar

@Inject
class ExampleNavGraphViewModel(
    private val bar: Bar,
) : ViewModel() {

    fun getSomething(): String {
        return "ExampleNavGraphViewModel@" + hashCode().toString(16)
    }
}
