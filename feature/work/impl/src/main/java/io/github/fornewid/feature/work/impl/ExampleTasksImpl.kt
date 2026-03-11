package io.github.fornewid.feature.work.impl

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.feature.work.ExampleTasks

@ContributesBinding(AppScope::class)
class ExampleTasksImpl @Inject constructor(
    private val workManager: WorkManager,
) : ExampleTasks {

    override fun executeExampleTask() {
        workManager.enqueue(OneTimeWorkRequestBuilder<ExampleWorker>().build())
    }
}
