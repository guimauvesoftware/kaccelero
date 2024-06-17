package dev.kaccelero.commons.responses

import io.ktor.server.application.*
import io.ktor.server.response.*

data class RedirectResponse(
    val url: String,
    val permanent: Boolean = false,
) : Exception(), ControllerResponse {

    override suspend fun respond(call: ApplicationCall) =
        call.respondRedirect(url, permanent)

}
