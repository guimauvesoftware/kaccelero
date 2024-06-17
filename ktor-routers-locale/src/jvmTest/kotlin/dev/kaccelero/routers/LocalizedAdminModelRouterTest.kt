package dev.kaccelero.routers

import dev.kaccelero.annotations.ModelKey
import dev.kaccelero.commons.localization.IGetLocaleForCallUseCase
import dev.kaccelero.controllers.IModelController
import dev.kaccelero.ktor.routers.admin.LocalizedAdminModelRouter
import dev.kaccelero.mocks.*
import dev.kaccelero.plugins.I18n
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
import io.mockk.every
import io.mockk.mockk
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalizedAdminModelRouterTest {

    private val mock = TestModel(1, "string")

    private fun installApp(application: ApplicationTestBuilder): HttpClient {
        application.install(I18n) {
            supportedLocales = listOf("en").map(Locale::forLanguageTag)
            useOfUri = true
        }
        application.application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
                json(kotlinx.serialization.json.Json)
            }
        }
        return application.createClient {
            followRedirects = false
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json(kotlinx.serialization.json.Json)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private inline fun <reified Keys> createRouter(
        controller: IModelController<TestModel, Long, TestCreatePayload, TestUpdatePayload>,
        getLocaleForCallUseCase: IGetLocaleForCallUseCase,
    ): LocalizedAdminModelRouter<TestModel, Long, TestCreatePayload, TestUpdatePayload> {
        return LocalizedAdminModelRouter(
            typeInfo<TestModel>(),
            typeInfo<TestCreatePayload>(),
            typeInfo<TestUpdatePayload>(),
            controller,
            ITestModelController::class,
            { template, model ->
                respond(
                    TemplateResponse(
                        template, TemplateResponseData(
                            (model["locale"] as Locale).language,
                            model["route"] as String,
                            model["keys"] as? List<Keys>,
                            model["item"] as? TestModel,
                            model["item"] as? String,
                            model["map"] as? String,
                            model["items"] as? List<TestModel>,
                            model["code"] as? Int,
                            model["error"] as? String
                        )
                    )
                )
            },
            getLocaleForCallUseCase,
            "error",
            "redirect={path}",
            "list",
            "get",
            "create",
            "update",
            "delete"
        )
    }

    @Test
    fun testRedirect() = testApplication {
        val client = installApp(this)
        val router = createRouter<ModelKey>(mockk(), mockk())
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/admin/testmodels")
        assertEquals(HttpStatusCode.Found, response.status)
    }

    @Test
    fun testLocaleEnglish() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestModelController>()
        val getLocaleForCallUseCase = mockk<IGetLocaleForCallUseCase>()
        val router = createRouter<ModelKey>(controller, getLocaleForCallUseCase)
        coEvery { controller.list(any()) } returns listOf(mock)
        every { getLocaleForCallUseCase(any()) } returns Locale.ENGLISH
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/en/admin/testmodels")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "adminList",
                TemplateResponseData(
                    "en",
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

}
