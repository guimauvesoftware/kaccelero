package dev.kaccelero.annotations

@Repeatable
@Target(AnnotationTarget.FUNCTION)
annotation class DocumentedError(
    val code: Int,
    val key: String,
    val description: String = "",
)
