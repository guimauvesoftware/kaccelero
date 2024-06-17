package dev.kaccelero.ktor.routers.websockets

import dev.kaccelero.ktor.models.ITestUnitController
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.serialization.json.Json
import kotlin.test.Test

class WebSocketAPIUnitRouterTest {

    private fun installApp(application: ApplicationTestBuilder): HttpClient {
        application.application {
            install(io.ktor.server.websocket.WebSockets)
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
                json(Json)
            }
        }
        return application.createClient {
            install(io.ktor.client.plugins.websocket.WebSockets)
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json(Json)
            }
        }
    }

    @Test
    fun testAPIBasicRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestUnitController>()
        val router = WebSocketAPIUnitRouter(
            controller,
            ITestUnitController::class
        )
        coEvery { controller.hello(any()) } returns Unit
        routing {
            router.createRoutes(this)
        }
        client.webSocket("/api/hello") {}
    }

}
