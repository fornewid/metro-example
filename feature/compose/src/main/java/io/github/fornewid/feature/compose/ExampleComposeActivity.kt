package io.github.fornewid.feature.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import io.github.fornewid.core.compose.ExampleTheme
import io.github.fornewid.core.metro.appGraph
import io.github.fornewid.feature.bar.Bar

class ExampleComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = appGraph<ComposeComponent>()

        setContent {
            ExampleTheme {
                Column {
                    // Option 1
                    StateHoisting.Screen(bar = component.bar)

                    // Option 2
                    CompositionLocal.Screen(bar = component.bar)

                    // Option 3
                    GraphAccess.Screen()
                }
            }
        }
    }
}

/**
 * https://developer.android.com/jetpack/compose/state#state-hoisting
 */
object StateHoisting {

    @Composable
    fun Screen(bar: Bar) {
        GrandParents(bar = bar)
    }

    @Composable
    private fun GrandParents(bar: Bar) {
        Parents(bar = bar)
    }

    @Composable
    private fun Parents(bar: Bar) {
        Children(bar = bar)
    }

    @Composable
    private fun Children(bar: Bar) {
        GrandChildren(bar = bar)
    }

    @Composable
    private fun GrandChildren(bar: Bar) {
        // Use bar
        Text(text = bar.toString())
    }
}

/**
 * https://developer.android.com/jetpack/compose/compositionlocal
 */
object CompositionLocal {

    @Composable
    fun Screen(bar: Bar) {
        CompositionLocalProvider(LocalBar provides bar) {
            GrandParents()
        }
    }

    @Composable
    private fun GrandParents() {
        Parents()
    }

    @Composable
    private fun Parents() {
        Children()
    }

    @Composable
    private fun Children() {
        GrandChildren()
    }

    @Composable
    private fun GrandChildren() {
        // Use bar
        val bar: Bar = LocalBar.current
        Text(text = bar.toString())
    }

    private val LocalBar = staticCompositionLocalOf<Bar> {
        error("CompositionLocal LocalBar not present")
    }
}

/**
 * Access dependencies from Metro graph directly
 */
object GraphAccess {

    @Composable
    fun Screen() {
        GrandParents()
    }

    @Composable
    private fun GrandParents() {
        Parents()
    }

    @Composable
    private fun Parents() {
        Children()
    }

    @Composable
    private fun Children() {
        GrandChildren()
    }

    @Composable
    private fun GrandChildren() {
        // Use bar
        val bar: Bar = rememberBar()
        Text(text = bar.toString())
    }

    @Composable
    private fun rememberBar(): Bar {
        val context = LocalContext.current
        return remember {
            context.appGraph<ComposeComponent>().bar
        }
    }
}
