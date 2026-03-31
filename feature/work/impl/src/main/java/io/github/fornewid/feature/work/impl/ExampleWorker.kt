package io.github.fornewid.feature.work.impl

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject
import dev.zacsweers.metro.Assisted
import io.github.fornewid.core.metro.InternalFeatureApi
import io.github.fornewid.feature.work.ExampleUseCase

@InternalFeatureApi
@AssistedInject
class ExampleWorker(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val exampleUseCase: ExampleUseCase,
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val success = exampleUseCase()
        return if (success) {
            Result.success()
        } else {
            Result.failure()
        }
    }

    @AssistedFactory
    fun interface Factory {
        fun create(context: Context, workerParams: WorkerParameters): ExampleWorker
    }
}
