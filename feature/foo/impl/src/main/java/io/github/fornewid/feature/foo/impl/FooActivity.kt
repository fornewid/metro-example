package io.github.fornewid.feature.foo.impl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import io.github.fornewid.core.metro.GraphViewModelFactory
import io.github.fornewid.core.metro.InternalFeatureApi
import io.github.fornewid.core.metro.appGraph
import io.github.fornewid.feature.foo.R

@OptIn(InternalFeatureApi::class)
internal class FooActivity : ComponentActivity(R.layout.foo_activity) {

    private val viewModel: FooViewModel by viewModels {
        GraphViewModelFactory { appGraph<FooComponent>().fooViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.doSomething()
    }
}
