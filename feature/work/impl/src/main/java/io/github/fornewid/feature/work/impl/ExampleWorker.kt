package io.github.fornewid.feature.work.impl

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.github.fornewid.core.kotlin.appGraph

class ExampleWorker(
    context: Context,
    workerParams: WorkerParameters,
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val component = applicationContext.appGraph<WorkerComponent>()
        val example = component.exampleUseCase()
        val success = example()
        return if (success) {
            Result.success()
        } else {
            Result.failure()
        }
    }
}
