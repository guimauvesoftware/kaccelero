package dev.kaccelero.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class ListModelPath(
    val path: String = "",
)
