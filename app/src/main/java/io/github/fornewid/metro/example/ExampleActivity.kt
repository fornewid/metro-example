package io.github.fornewid.metro.example

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.github.fornewid.core.kotlin.GraphViewModelFactory
import io.github.fornewid.core.kotlin.appGraph
import io.github.fornewid.metro.example.databinding.ExampleActivityBinding
import io.github.fornewid.feature.compose.ExampleComposeActivity
import io.github.fornewid.feature.compose.advanced.AdvancedExampleComposeActivity
import io.github.fornewid.feature.navigation.compose.ExampleNavigationComposeActivity
import io.github.fornewid.feature.navigation.fragment.ExampleNavigationFragmentActivity

class ExampleActivity : AppCompatActivity() {

    private val graph by lazy { appGraph<AppGraph>() }

    private val viewModel: ExampleViewModel by viewModels {
        GraphViewModelFactory { graph.exampleViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ExampleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.doSomething()

        binding.foo.setOnClickListener {
            startActivity(graph.fooNavigator.createIntent(this))
        }
        binding.bar.setOnClickListener {
            startActivity(graph.barNavigator.createIntent(this))
        }
        binding.navigationFragment.setOnClickListener {
            startActivity(Intent(this, ExampleNavigationFragmentActivity::class.java))
        }
        binding.navigationCompose.setOnClickListener {
            startActivity(Intent(this, ExampleNavigationComposeActivity::class.java))
        }
        binding.composeBasic.setOnClickListener {
            startActivity(Intent(this, ExampleComposeActivity::class.java))
        }
        binding.composeAdvanced.setOnClickListener {
            startActivity(Intent(this, AdvancedExampleComposeActivity::class.java))
        }
    }
}
