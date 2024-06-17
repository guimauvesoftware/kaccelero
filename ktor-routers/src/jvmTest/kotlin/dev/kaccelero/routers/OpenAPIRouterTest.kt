package dev.kaccelero.routers

import dev.kaccelero.mocks.TestModel
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.swagger.v3.oas.models.OpenAPI
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

class OpenAPIRouterTest {

    @Test
    fun testOpenAPIRouter() = testApplication {
        val openAPI = OpenAPI().info {
            title = "Test"
            description = "Test description"
            version = "1.0.0"
        }
        openAPI.schema(typeOf<TestModel>())
        val router = OpenAPIRouter()
        routing {
            router.createRoutes(this, openAPI)
        }
        val response = client.get("/docs")
        assertEquals(HttpStatusCode.OK, response.status)
    }

}
