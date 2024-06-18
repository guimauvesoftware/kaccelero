package dev.kaccelero.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class GetModelPath(
    val path: String = "",
)
