package dev.kaccelero.mocks

import dev.kaccelero.annotations.PayloadProperty

data class AnnotatedUpdatePayloadTest(
    @PayloadProperty("string")
    val value: String,
)
