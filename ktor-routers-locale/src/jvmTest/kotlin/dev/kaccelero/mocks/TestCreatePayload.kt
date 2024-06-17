package dev.kaccelero.mocks

import dev.kaccelero.annotations.PayloadProperty
import kotlinx.serialization.Serializable

@Serializable
data class TestCreatePayload(
    @PayloadProperty("string")
    val string: String,
)
