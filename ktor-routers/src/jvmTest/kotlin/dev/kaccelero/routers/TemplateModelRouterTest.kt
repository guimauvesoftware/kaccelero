package dev.kaccelero.routers

import dev.kaccelero.annotations.ModelKey
import dev.kaccelero.annotations.PayloadKey
import dev.kaccelero.commons.exceptions.ControllerException
import dev.kaccelero.controllers.IModelController
import dev.kaccelero.mocks.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.testing.*
import io.ktor.util.reflect.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class TemplateModelRouterTest {

    private val mock = TestModel(1, "string")
    private val createMock = TestCreatePayload("string")
    private val updateMock = TestUpdatePayload("string")

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
        controller: IModelController<TestModel, Long, TestCreatePayload, TestUpdatePayload>,
    ) = TemplateModelRouter(
        typeInfo<TestModel>(),
        typeInfo<TestCreatePayload>(),
        typeInfo<TestUpdatePayload>(),
        controller,
        ITestModelController::class,
        { template, model ->
            respond(
                TemplateResponse(
                    template, TemplateResponseData(
                        model["route"] as String,
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
        "redirect={path}"
    )

    @Test
    fun testTemplateBasicRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.basic(any()) } returns "Hello world"
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/basic")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "basic",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    keys = listOf(
                        ModelKey("id", "id", ""),
                        ModelKey("string", "string", "")
                    ),
                    itemString = "Hello world",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateBasicMapRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.basicMap(any()) } returns mapOf("map" to "Hello world")
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/basic/map")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "basic",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    keys = listOf(
                        ModelKey("id", "id", ""),
                        ModelKey("string", "string", "")
                    ),
                    itemMap = "Hello world",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateBasicModelRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.basicModel(any()) } returns TestUser("userId")
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/basic/model")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "basic",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    keys = listOf(
                        ModelKey("id", "id", ""),
                        ModelKey("string", "string", "")
                    ),
                    itemModel = TestUser("userId"),
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateGetRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.list(any()) } returns listOf(mock)
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "list",
                TemplateResponseData(
                    "testmodels",
                    keys = listOf(
                        ModelKey("id", "id", ""),
                        ModelKey("string", "string", "")
                    ),
                    items = listOf(mock),
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateGetRouteControllerException() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.list(any()) } throws ControllerException(
            HttpStatusCode.NotFound,
            "error_mock"
        )
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels")
        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals(
            TemplateResponse(
                "error",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    code = HttpStatusCode.NotFound.value,
                    error = "error_mock",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateGetRouteUnauthorized() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.list(any()) } throws ControllerException(
            HttpStatusCode.Unauthorized,
            "error_mock"
        )
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels")
        assertEquals(HttpStatusCode.Found, response.status)
    }

    @Test
    fun testTemplateGetCreateRoute() = testApplication {
        val client = installApp(this)
        val router = createRouter<PayloadKey>(mockk())
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/create")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "create",
                TemplateResponseData<TestModel, PayloadKey>(
                    "testmodels",
                    keys = listOf(
                        PayloadKey("string", "string", "", true)
                    )
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplatePostCreateRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<PayloadKey>(controller)
        coEvery { controller.create(any(), createMock) } returns mock
        routing {
            router.createRoutes(this)
        }
        val response = client.post("/testmodels/create") {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(
                listOf(
                    "string" to "string"
                ).formUrlEncode()
            )
        }
        assertEquals(HttpStatusCode.Found, response.status)
        coVerify { controller.create(any(), createMock) }
    }

    @Test
    fun testTemplatePostCreateRouteBadValidator() = testApplication {
        val client = installApp(this)
        val router = createRouter<PayloadKey>(mockk())
        routing {
            router.createRoutes(this)
        }
        val response = client.post("/testmodels/create") {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(
                listOf(
                    "string" to "123"
                ).formUrlEncode()
            )
        }
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals(
            TemplateResponse(
                "error",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    code = HttpStatusCode.BadRequest.value,
                    error = "testmodels_string_regex",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplatePostCreateRouteBadRequest() = testApplication {
        val client = installApp(this)
        val router = createRouter<PayloadKey>(mockk())
        routing {
            router.createRoutes(this)
        }
        val response = client.post("/testmodels/create") {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(
                listOf(
                    "other" to "string"
                ).formUrlEncode()
            )
        }
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals(
            TemplateResponse(
                "error",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    code = HttpStatusCode.BadRequest.value,
                    error = "error_body_invalid",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateGetIdRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.get(any(), 1) } returns mock
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/1")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "get",
                TemplateResponseData(
                    "testmodels",
                    keys = listOf(
                        ModelKey("id", "id", ""),
                        ModelKey("string", "string", "")
                    ),
                    item = mock,
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateGetIdRouteControllerException() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.get(any(), 1) } throws ControllerException(
            HttpStatusCode.NotFound,
            "error_mock"
        )
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/1")
        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals(
            TemplateResponse(
                "error",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    code = HttpStatusCode.NotFound.value,
                    error = "error_mock",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateGetIdUpdateRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<PayloadKey>(controller)
        coEvery { controller.get(any(), 1) } returns mock
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/1/update")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "update",
                TemplateResponseData(
                    "testmodels",
                    keys = listOf(
                        PayloadKey("string", "string", "", true)
                    ),
                    item = mock,
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateGetIdUpdateRouteControllerException() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<PayloadKey>(controller)
        coEvery { controller.get(any(), 1) } throws ControllerException(
            HttpStatusCode.NotFound,
            "error_mock"
        )
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/1/update")
        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals(
            TemplateResponse(
                "error",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    code = HttpStatusCode.NotFound.value,
                    error = "error_mock",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplatePostIdUpdateRoute() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<PayloadKey>(controller)
        coEvery { controller.update(any(), 1, updateMock) } returns mock
        routing {
            router.createRoutes(this)
        }
        val response = client.post("/testmodels/1/update") {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(
                listOf(
                    "string" to "string"
                ).formUrlEncode()
            )
        }
        assertEquals(HttpStatusCode.Found, response.status)
        coVerify { controller.update(any(), 1, updateMock) }
    }

    @Test
    fun testTemplatePostIdUpdateRouteBadRequest() = testApplication {
        val client = installApp(this)
        val router = createRouter<ModelKey>(mockk())
        routing {
            router.createRoutes(this)
        }
        val response = client.post("/testmodels/1/update") {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(
                listOf(
                    "other" to "string"
                ).formUrlEncode()
            )
        }
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals(
            TemplateResponse(
                "error",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    code = HttpStatusCode.BadRequest.value,
                    error = "error_body_invalid",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateGetIdRouteDelete() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.get(any(), 1) } returns mock
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/1/delete")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "delete",
                TemplateResponseData(
                    "testmodels",
                    keys = listOf(
                        ModelKey("id", "id", ""),
                        ModelKey("string", "string", "")
                    ),
                    item = mock,
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplateGetIdRouteDeleteControllerException() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.get(any(), 1) } throws ControllerException(
            HttpStatusCode.NotFound,
            "error_mock"
        )
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels/1/delete")
        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals(
            TemplateResponse(
                "error",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    code = HttpStatusCode.NotFound.value,
                    error = "error_mock",
                )
            ), response.body()
        )
    }

    @Test
    fun testTemplatePostIdRouteDelete() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.delete(any(), 1) } returns Unit
        routing {
            router.createRoutes(this)
        }
        val response = client.post("/testmodels/1/delete")
        assertEquals(HttpStatusCode.Found, response.status)
        coVerify { controller.delete(any(), 1) }
    }

    @Test
    fun testTemplatePostIdRouteDeleteControllerException() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val router = createRouter<ModelKey>(controller)
        coEvery { controller.delete(any(), 1) } throws ControllerException(
            HttpStatusCode.NotFound,
            "error_mock"
        )
        routing {
            router.createRoutes(this)
        }
        val response = client.post("/testmodels/1/delete")
        assertEquals(HttpStatusCode.NotFound, response.status)
        assertEquals(
            TemplateResponse(
                "error",
                TemplateResponseData<TestModel, ModelKey>(
                    "testmodels",
                    code = HttpStatusCode.NotFound.value,
                    error = "error_mock",
                )
            ), response.body()
        )
    }

}
