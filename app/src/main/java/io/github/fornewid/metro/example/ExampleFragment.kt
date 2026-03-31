package io.github.fornewid.metro.example

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.github.fornewid.core.metro.GraphViewModelFactory
import io.github.fornewid.core.metro.appGraph

class ExampleFragment : Fragment(R.layout.example_fragment) {

    private val viewModel: ExampleViewModel by viewModels {
        GraphViewModelFactory {
            requireContext().appGraph<AppGraph>().exampleViewModel()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.doSomething()
    }
}
