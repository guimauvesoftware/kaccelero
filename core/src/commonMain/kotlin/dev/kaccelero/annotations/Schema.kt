package dev.kaccelero.annotations

@Target(AnnotationTarget.PROPERTY)
annotation class Schema(
    val name: String,
    val example: String = "",
)
