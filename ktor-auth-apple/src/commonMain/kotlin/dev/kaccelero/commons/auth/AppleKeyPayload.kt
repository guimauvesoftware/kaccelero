package dev.kaccelero.commons.auth

import kotlinx.serialization.Serializable

@Serializable
data class AppleKeyPayload(
    val keys: List<AppleKey>,
)
