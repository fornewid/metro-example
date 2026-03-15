package io.github.fornewid.metro.example

import android.app.Application
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.core.kotlin.MetroWorkerFactory
import io.github.fornewid.feature.bar.Bar
import io.github.fornewid.feature.bar.BarNavigator
import io.github.fornewid.feature.foo.FooNavigator
import io.github.fornewid.feature.work.ExampleTasks

@DependencyGraph(AppScope::class)
interface AppGraph {

    val fooNavigator: FooNavigator
    val barNavigator: BarNavigator
    val bar: Bar
    val exampleTasks: ExampleTasks
    val workerFactory: MetroWorkerFactory
    fun exampleViewModel(): ExampleViewModel

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Provides application: Application): AppGraph
    }
}
