package io.github.fornewid.feature.work.impl

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.fornewid.core.metro.AppScope
import io.github.fornewid.core.metro.InternalFeatureApi
import io.github.fornewid.feature.work.ExampleTasks

@InternalFeatureApi
@ContributesBinding(AppScope::class)
@Inject
class ExampleTasksImpl(
    private val workManager: WorkManager,
) : ExampleTasks {

    override fun executeExampleTask() {
        workManager.enqueue(OneTimeWorkRequestBuilder<ExampleWorker>().build())
    }
}
