package dev.kaccelero.mocks

import dev.kaccelero.annotations.PayloadProperty

data class AnnotatedCreatePayloadTest(
    @PayloadProperty("string")
    val value: String,
)
