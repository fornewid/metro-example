package io.github.fornewid.core.kotlin

@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is an internal implementation. Use the interface via DI instead of referencing directly.",
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.CONSTRUCTOR)
annotation class InternalFeatureApi
