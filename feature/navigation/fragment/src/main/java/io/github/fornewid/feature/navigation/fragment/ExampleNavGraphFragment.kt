package io.github.fornewid.feature.navigation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.github.fornewid.core.kotlin.GraphViewModelFactory
import io.github.fornewid.core.kotlin.appGraph

class ExampleNavGraphFragment : Fragment(R.layout.example_nav_graph_fragment) {

    private val viewModel: ExampleNavGraphViewModel by viewModels {
        GraphViewModelFactory {
            requireContext().appGraph<NavFragmentComponent>().navFragmentViewModel()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.doSomething()
    }
}
