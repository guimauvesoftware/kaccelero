package dev.kaccelero.annotations

@Target(AnnotationTarget.FUNCTION)
annotation class TemplateMapping(
    val template: String,
)
