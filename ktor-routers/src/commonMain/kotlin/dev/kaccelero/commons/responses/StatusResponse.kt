package dev.kaccelero.commons.responses

import io.ktor.http.*

data class StatusResponse<T : Any>(
    val status: HttpStatusCode,
    val content: T,
)
