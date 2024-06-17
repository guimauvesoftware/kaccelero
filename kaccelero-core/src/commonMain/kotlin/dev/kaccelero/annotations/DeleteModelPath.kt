package dev.kaccelero.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class DeleteModelPath(
    val path: String = "",
)
