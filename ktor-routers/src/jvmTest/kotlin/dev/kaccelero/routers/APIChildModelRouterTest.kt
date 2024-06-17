package dev.kaccelero.routers

import dev.kaccelero.controllers.IChildModelController
import dev.kaccelero.controllers.IModelController
import dev.kaccelero.mocks.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import io.ktor.util.reflect.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class APIChildModelRouterTest {

    private val mock = TestModel(1, "string")
    private val childMock = TestChildModel(2, 1, "string")
    private val createMock = TestCreatePayload("string")
    private val updateMock = TestUpdatePayload("string")

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

    private fun createChildRouter(
        controller: IChildModelController<TestChildModel, Long, TestCreatePayload, TestUpdatePayload, TestModel, Long>,
        parentRouter: APIChildModelRouter<TestModel, Long, *, *, *, *>,
        route: String? = null,
        prefix: String? = null,
    ) = APIChildModelRouter(
        typeInfo<TestChildModel>(),
        typeInfo<TestCreatePayload>(),
        typeInfo<TestUpdatePayload>(),
        controller,
        ITestChildModelController::class,
        parentRouter,
        route,
        null,
        prefix
    )

    private fun createRouter(
        controller: IModelController<TestModel, Long, TestCreatePayload, TestUpdatePayload>,
    ): APIModelRouter<TestModel, Long, TestCreatePayload, TestUpdatePayload> {
        return APIModelRouter(
            typeInfo<TestModel>(),
            typeInfo<TestCreatePayload>(),
            typeInfo<TestUpdatePayload>(),
            controller,
            ITestModelController::class
        )
    }

    @Test
    fun testAPIBasicRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val childController = mockk<ITestChildModelController>()
        val router = createChildRouter(childController, createRouter(controller))
        coEvery { controller.get(any(), 1) } returns mock
        coEvery { childController.basic(any(), mock) } returns "Hello world"
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/api/testmodels/1/testchildmodels/basic")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello world", response.body())
    }

    @Test
    fun testAPIGetRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val childController = mockk<ITestChildModelController>()
        val router = createChildRouter(childController, createRouter(controller))
        coEvery { controller.get(any(), 1) } returns mock
        coEvery { childController.list(any(), mock) } returns listOf(childMock)
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/api/testmodels/1/testchildmodels")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(listOf(childMock), response.body())
    }

    @Test
    fun testAPIGetRouteCustomRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val childController = mockk<ITestChildModelController>()
        val router = createChildRouter(childController, createRouter(controller), route = "childs")
        coEvery { controller.get(any(), 1) } returns mock
        coEvery { childController.list(any(), mock) } returns listOf(childMock)
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/api/testmodels/1/childs")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(listOf(childMock), response.body())
    }

    @Test
    fun testAPIGetRouteCustomPrefix() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val childController = mockk<ITestChildModelController>()
        val router = createChildRouter(childController, createRouter(controller), route = "childs", prefix = "/api/v1")
        coEvery { controller.get(any(), 1) } returns mock
        coEvery { childController.list(any(), mock) } returns listOf(childMock)
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/api/v1/testmodels/1/childs")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(listOf(childMock), response.body())
    }

    @Test
    fun testAPIGetIdRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val childController = mockk<ITestChildModelController>()
        val router = createChildRouter(childController, createRouter(controller))
        coEvery { controller.get(any(), 1) } returns mock
        coEvery { childController.get(any(), mock, 2) } returns childMock
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/api/testmodels/1/testchildmodels/2")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(childMock, response.body())
    }

}
