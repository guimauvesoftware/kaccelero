package dev.kaccelero.annotations

@Target(AnnotationTarget.PROPERTY)
annotation class PayloadProperty(
    val type: String,
    val style: String = "",
)
