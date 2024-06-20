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
import io.ktor.server.testing.*
import io.ktor.util.reflect.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ConcatModelRouterTest {

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

    @Test
    fun testRouterOf() {
        val apiRouter = APIModelRouter(
            typeInfo<TestModel>(),
            typeInfo<TestCreatePayload>(),
            typeInfo<TestUpdatePayload>(),
            mockk(),
            ITestModelController::class
        )
        val router = ConcatModelRouter(apiRouter)
        assertEquals(apiRouter, router.routerOf())
        assertFailsWith(NoSuchElementException::class) {
            router.routerOf<ConcatModelRouter<*, *, *, *>>()
        }
    }

    @Test
    fun testRouterOfOrNull() {
        val apiRouter = APIModelRouter(
            typeInfo<TestModel>(),
            typeInfo<TestCreatePayload>(),
            typeInfo<TestUpdatePayload>(),
            mockk(),
            ITestModelController::class
        )
        val router = ConcatModelRouter(apiRouter)
        assertEquals(apiRouter, router.routerOfOrNull())
        assertEquals(null as ConcatModelRouter<*, *, *, *>?, router.routerOfOrNull())
    }

    @Test
    fun testAPIGetRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = ConcatModelRouter(
            APIModelRouter(
                typeInfo<TestModel>(),
                typeInfo<TestCreatePayload>(),
                typeInfo<TestUpdatePayload>(),
                controller,
                ITestModelController::class
            )
        )
        coEvery { controller.list(any()) } returns listOf(mock)
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/api/testmodels")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(listOf(mock), response.body())
    }

}
