package dev.kaccelero.mocks

import dev.kaccelero.annotations.PayloadProperty
import kotlinx.serialization.Serializable

@Serializable
data class TestUpdatePayload(
    @PayloadProperty("string")
    val string: String,
)
