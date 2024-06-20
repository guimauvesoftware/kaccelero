package dev.kaccelero.commons.exceptions

import io.ktor.http.*

data class APIException(
    val code: HttpStatusCode,
    val key: String,
) : Exception()
