package dev.kaccelero.mocks

import dev.kaccelero.annotations.PayloadProperty
import dev.kaccelero.annotations.StringPropertyValidator
import kotlinx.serialization.Serializable

@Serializable
data class TestCreatePayload(
    @PayloadProperty("string") @StringPropertyValidator(regex = "[a-z]+")
    val string: String,
)
