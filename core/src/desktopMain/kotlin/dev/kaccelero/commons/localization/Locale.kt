package dev.kaccelero.commons.localization

import kotlinx.serialization.Serializable

@Serializable
actual data class Locale(
    val language: String,
)
