package dev.kaccelero.annotations

@Target(AnnotationTarget.PROPERTY)
annotation class StringPropertyValidator(
    val regex: String = ".*",
    val minLength: Int = 0,
    val maxLength: Int = Int.MAX_VALUE,
)
