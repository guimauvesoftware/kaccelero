package dev.kaccelero.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class WebSocketMapping(
    val operationId: String = "",
    val description: String = "",
)
