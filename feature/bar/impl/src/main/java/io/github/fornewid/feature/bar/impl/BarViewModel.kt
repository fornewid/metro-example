package io.github.fornewid.feature.bar.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.Inject
import io.github.fornewid.core.kotlin.InternalFeatureApi
import io.github.fornewid.data.ExampleRepository
import kotlinx.coroutines.launch

@InternalFeatureApi
@Inject
class BarViewModel(
    private val exampleRepository: ExampleRepository,
) : ViewModel() {

    fun doSomething() {
        viewModelScope.launch {
            Log.d("BarViewModel", "doSomething: " + exampleRepository.getSomething())
        }
    }
}
