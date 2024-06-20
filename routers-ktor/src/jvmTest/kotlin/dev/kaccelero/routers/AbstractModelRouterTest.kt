package dev.kaccelero.routers

import dev.kaccelero.mocks.ITestModelController
import dev.kaccelero.mocks.TestCreatePayload
import dev.kaccelero.mocks.TestModel
import dev.kaccelero.mocks.TestUpdatePayload
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import io.ktor.util.reflect.*
import io.mockk.coEvery
import io.mockk.mockk
import io.swagger.v3.oas.models.OpenAPI
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass
import kotlin.test.Test
import kotlin.test.assertEquals

class AbstractModelRouterTest {

    private val mock = TestModel(1, "string")

    private fun installApp(application: ApplicationTestBuilder): HttpClient {
        application.application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
                json(Json)
            }
        }
        return application.createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json(Json)
            }
        }
    }

    private fun createRouter(
        route: String?,
        id: String?,
        prefix: String?,
    ): AbstractModelRouter<TestModel, Long, TestCreatePayload, TestUpdatePayload> {
        val controller = mockk<ITestModelController>()
        coEvery { controller.list(any()) } returns emptyList()
        return object : AbstractModelRouter<TestModel, Long, TestCreatePayload, TestUpdatePayload>(
            typeInfo<TestModel>(),
            typeInfo<TestCreatePayload>(),
            typeInfo<TestUpdatePayload>(),
            controller,
            ITestModelController::class,
            route,
            id,
            prefix
        ) {
            override fun createRoutes(root: IRoute, openAPI: IOpenAPI?) {
                if (root !is KtorRoute) return
                root.route.get("$fullRoute/{${this.id}}") {
                    call.respond(mock)
                }
            }

            override fun createControllerRoute(root: Route, controllerRoute: ControllerRoute, openAPI: OpenAPI?) {}

            override suspend fun <Payload : Any> decodePayload(call: ApplicationCall, type: KClass<Payload>): Payload {
                throw NotImplementedError()
            }
        }
    }

    @Test
    fun testNoArgs() = testApplication {
        val client = installApp(this)
        val router = createRouter(null, null, null)
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/1")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(mock, response.body())
    }

    @Test
    fun testPrefix() = testApplication {
        val client = installApp(this)
        val router = createRouter(null, null, "prefix")
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/prefix/testmodels/1")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(mock, response.body())
    }

    @Test
    fun testId() = testApplication {
        val client = installApp(this)
        val router = createRouter(null, "customId", null)
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/1")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(mock, response.body())
    }

    @Test
    fun testRoute() = testApplication {
        val client = installApp(this)
        val router = createRouter("customRoute", null, null)
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/customRoute/1")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(mock, response.body())
    }

    @Test
    fun testAll() = testApplication {
        val client = installApp(this)
        val router = createRouter("customRoute", "customId", "prefix")
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/prefix/customRoute/1")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(mock, response.body())
    }

}
