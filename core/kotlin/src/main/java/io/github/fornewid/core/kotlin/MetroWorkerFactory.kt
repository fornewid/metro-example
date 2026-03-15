package io.github.fornewid.core.kotlin

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

fun interface ChildWorkerFactory {
    fun create(appContext: Context, workerParams: WorkerParameters): ListenableWorker
}

@SingleIn(AppScope::class)
@Inject
class MetroWorkerFactory(
    private val workerFactories: Map<String, ChildWorkerFactory>,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return workerFactories[workerClassName]?.create(appContext, workerParameters)
    }
}
