package dev.kaccelero.commons.auth

import kotlinx.serialization.Serializable

@Serializable
data class VerifyPasswordPayload(
    val password: String,
    val hash: String,
)
