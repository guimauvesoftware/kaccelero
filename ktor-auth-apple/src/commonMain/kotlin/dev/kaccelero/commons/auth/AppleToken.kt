package dev.kaccelero.commons.auth

import kotlinx.serialization.Serializable

@Serializable
data class AppleToken(
    val token: String,
    val appleId: String,
)
