package dev.kaccelero.commons.responses

import io.ktor.server.application.*

interface ControllerResponse {

    suspend fun respond(call: ApplicationCall)

}
