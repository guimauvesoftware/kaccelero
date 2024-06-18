package dev.kaccelero.mocks

import dev.kaccelero.annotations.Path
import dev.kaccelero.annotations.WebSocketMapping
import dev.kaccelero.controllers.IUnitController
import io.ktor.server.websocket.*

interface ITestUnitController : IUnitController {

    @WebSocketMapping
    @Path("GET", "/hello")
    suspend fun hello(session: DefaultWebSocketServerSession)

}
