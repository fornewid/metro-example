package io.github.fornewid.metro.example

import android.content.Context
import android.util.AttributeSet
import android.view.View
import io.github.fornewid.core.metro.appGraph
import io.github.fornewid.feature.bar.Bar

class ExampleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    val bar: Bar = context.appGraph<AppGraph>().bar
}
