package io.github.fornewid.feature.work.impl

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.fornewid.core.kotlin.AppScope
import io.github.fornewid.feature.bar.Bar

interface ExampleUseCase {
    operator fun invoke(): Boolean
}

@ContributesBinding(AppScope::class)
@Inject
class ExampleUseCaseImpl(
    private val bar: Bar,
) : ExampleUseCase {

    override fun invoke(): Boolean {
        bar.toString()
        return true
    }
}
