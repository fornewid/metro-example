package io.github.fornewid.feature.work.impl

import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.IntoMap
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.StringKey
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.core.kotlin.ChildWorkerFactory
import io.github.fornewid.core.kotlin.InternalFeatureApi

@InternalFeatureApi
@ContributesTo(AppScope::class)
interface WorkerComponent {

    @Provides
    @IntoMap
    @StringKey("io.github.fornewid.feature.work.impl.ExampleWorker")
    fun bindExampleWorkerFactory(factory: ExampleWorker.Factory): ChildWorkerFactory {
        return ChildWorkerFactory { context, workerParams ->
            factory.create(context, workerParams)
        }
    }
}
