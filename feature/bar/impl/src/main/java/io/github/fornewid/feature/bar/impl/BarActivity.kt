package io.github.fornewid.feature.bar.impl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import io.github.fornewid.core.metro.GraphViewModelFactory
import io.github.fornewid.core.metro.InternalFeatureApi
import io.github.fornewid.core.metro.appGraph
import io.github.fornewid.feature.bar.R

@OptIn(InternalFeatureApi::class)
internal class BarActivity : ComponentActivity(R.layout.bar_activity) {

    private val viewModel: BarViewModel by viewModels {
        GraphViewModelFactory { appGraph<BarComponent>().barViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.doSomething()
    }
}
