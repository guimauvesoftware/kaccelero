package dev.kaccelero.mocks

import dev.kaccelero.annotations.StringPropertyValidator

data class ValidatedCreatePayload(
    @StringPropertyValidator(minLength = 3)
    val name: String,
)
