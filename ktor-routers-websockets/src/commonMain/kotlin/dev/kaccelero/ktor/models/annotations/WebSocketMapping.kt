package dev.kaccelero.ktor.models.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class WebSocketMapping(
    val operationId: String = "",
    val description: String = "",
)
