package dev.kaccelero.annotations

data class PropertyValidatorException(
    val key: String,
    val value: Any,
    val validator: Any,
    val reason: String,
) : Exception()
