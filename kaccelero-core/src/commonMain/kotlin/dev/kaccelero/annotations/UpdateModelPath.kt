package dev.kaccelero.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class UpdateModelPath(
    val path: String = "",
)
