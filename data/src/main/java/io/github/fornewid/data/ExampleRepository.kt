package io.github.fornewid.data

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.core.kotlin.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface ExampleRepository {
    suspend fun getSomething(): String
}

@ContributesBinding(AppScope::class)
@Inject
class ExampleRepositoryImpl(
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ExampleRepository {

    override suspend fun getSomething(): String {
        return withContext(ioDispatcher) {
            delay(500)
            "something"
        }
    }
}
