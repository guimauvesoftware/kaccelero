package dev.kaccelero.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class DocumentedTag(
    val name: String,
)
