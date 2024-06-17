package dev.kaccelero.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class Path(
    val method: String,
    val path: String,
)
