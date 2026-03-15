package io.github.fornewid.feature.compose.advanced

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Inject
import io.github.fornewid.core.compose.ExampleTheme
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.core.kotlin.appGraph
import io.github.fornewid.feature.bar.Bar

class AdvancedExampleComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExampleTheme {
                ComposableScoped.Screen()
            }
        }
    }
}

@ContributesTo(AppScope::class)
interface AdvancedComposeComponent {
    fun exampleStateHolder(): ExampleStateHolder
}

@Inject
class ExampleStateHolder(
    val bar: Bar,
) {
    override fun toString(): String {
        return "ExampleStateHolder@" + hashCode().toString(16)
    }
}

private val LocalComponent = staticCompositionLocalOf<AdvancedComposeComponent> {
    error("CompositionLocal LocalComponent not present")
}

object ComposableScoped {

    @Composable
    fun Screen() {
        val context = LocalContext.current
        val component = remember { context.appGraph<AdvancedComposeComponent>() }
        CompositionLocalProvider(LocalComponent provides component) {
            GrandParents()
        }
    }

    @Composable
    private fun GrandParents() {
        val stateHolder = rememberStateHolder()
        Column {
            Text(
                text = "GrandParents: $stateHolder",
                color = Color.Red,
            )
            Parents()
        }
    }

    @Composable
    private fun Parents() {
        val stateHolder = rememberStateHolder()
        Column {
            Text(
                text = "Parents: $stateHolder",
                color = Color.Green,
            )
            Children()
        }
    }

    @Composable
    private fun Children() {
        val stateHolder = rememberStateHolder()
        Column {
            Text(
                text = "Children: $stateHolder",
                color = Color.Blue,
            )
            GrandChildren()
        }
    }

    @Composable
    private fun GrandChildren() {
        val stateHolder = rememberStateHolder()
        Text(
            text = "GrandChildren: $stateHolder",
        )
    }

    @Composable
    private fun rememberStateHolder(): ExampleStateHolder {
        val component = LocalComponent.current
        return remember {
            component.exampleStateHolder()
        }
    }
}
