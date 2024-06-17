package dev.kaccelero.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class CreateModelPath(
    val path: String = "",
)
