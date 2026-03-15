package io.github.fornewid.feature.navigation.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.fornewid.core.compose.ExampleTheme

class ExampleNavigationComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleTheme {
                ExampleNavGraph()
            }
        }
    }
}
