package dev.kaccelero.ktor.models

import dev.kaccelero.annotations.Path
import dev.kaccelero.controllers.IUnitController
import dev.kaccelero.ktor.models.annotations.WebSocketMapping
import io.ktor.server.websocket.*

interface ITestUnitController : IUnitController {

    @WebSocketMapping
    @Path("GET", "/hello")
    suspend fun hello(session: DefaultWebSocketServerSession)

}
