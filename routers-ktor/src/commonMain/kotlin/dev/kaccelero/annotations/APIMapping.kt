package dev.kaccelero.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class APIMapping(
    val operationId: String = "",
    val description: String = "",
)
