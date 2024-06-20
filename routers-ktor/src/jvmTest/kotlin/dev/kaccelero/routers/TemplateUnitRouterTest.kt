package dev.kaccelero.routers

import dev.kaccelero.annotations.ModelKey
import dev.kaccelero.commons.responses.RedirectResponse
import dev.kaccelero.controllers.IUnitController
import dev.kaccelero.mocks.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class TemplateUnitRouterTest {

    private fun installApp(application: ApplicationTestBuilder): HttpClient {
        application.application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
                json(Json)
            }
        }
        return application.createClient {
            followRedirects = false
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json(Json)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private inline fun <reified Keys> createRouter(
        controller: IUnitController,
        route: String? = null,
    ) = TemplateUnitRouter(
        controller,
        ITestUnitController::class,
        { template, model ->
            respond(
                TemplateResponse(
                    template, TemplateResponseData(
                        model["route"] as String,
                        null,
                        model["keys"] as? List<Keys>,
                        model["item"] as? TestModel,
                        model["item"] as? String,
                        model["map"] as? String,
                        model["item"] as? TestUser,
                        model["items"] as? List<TestModel>,
                        model["code"] as? Int,
                        model["error"] as? String
                    )
                )
            )
        },
        "error",
        "redirect={path}",
        route
    )

    @Test
    fun testTemplateBasicRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestUnitController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.hello() } returns "Hello world"
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/hello")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "hello.ftl",
                TemplateResponseData<TestModel, ModelKey>(
                    "",
                    keys = listOf(),
                    itemString = "Hello world",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateBasicRouteRedirect() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestUnitController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.hello() } throws RedirectResponse("/redirect")
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/hello")
        assertEquals(HttpStatusCode.Found, response.status)
    }

    @Test
    fun testTemplateBasicRouteRedirectPermanent() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestUnitController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.hello() } throws RedirectResponse("/redirect", true)
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/hello")
        assertEquals(HttpStatusCode.MovedPermanently, response.status)
    }

    @Test
    fun testTemplateBasicRouteCustomRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestUnitController>()
        val router = createRouter<ModelKey>(controller, "test")
        coEvery { controller.hello() } returns "Hello world"
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/test/hello")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "hello.ftl",
                TemplateResponseData<TestModel, ModelKey>(
                    "test",
                    keys = listOf(),
                    itemString = "Hello world",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplatePostBasicRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestUnitController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.postHello(TestCreatePayload("string")) } returns "Hello world"
        routing {
            router.createRoutes(this)
        }
        val response = client.post("/hello") {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(
                listOf(
                    "string" to "string"
                ).formUrlEncode()
            )
        }
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "hello.ftl",
                TemplateResponseData<TestModel, ModelKey>(
                    "",
                    keys = listOf(),
                    itemString = "Hello world",
                )
            ), response.body()
        )
    }

}
