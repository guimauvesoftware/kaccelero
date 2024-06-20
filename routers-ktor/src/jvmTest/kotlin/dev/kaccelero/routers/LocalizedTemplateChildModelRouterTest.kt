package dev.kaccelero.routers

import dev.kaccelero.annotations.ModelKey
import dev.kaccelero.commons.localization.IGetLocaleForCallUseCase
import dev.kaccelero.controllers.IModelController
import dev.kaccelero.mocks.*
import dev.kaccelero.models.UnitModel
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

class LocalizedTemplateChildModelRouterTest {

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
    ): LocalizedTemplateChildModelRouter<TestModel, Long, TestCreatePayload, TestUpdatePayload, UnitModel, Unit> {
        return LocalizedTemplateChildModelRouter(
            typeInfo<TestModel>(),
            typeInfo<TestCreatePayload>(),
            typeInfo<TestUpdatePayload>(),
            controller,
            ITestModelController::class,
            null,
            { template, model ->
                respond(
                    TemplateResponse(
                        template, TemplateResponseData(
                            model["route"] as String,
                            (model["locale"] as Locale).language,
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
            getLocaleForCallUseCase,
            "error",
            "redirect={path}"
        )
    }

    @Test
    fun testRedirect() = testApplication {
        val client = installApp(this)
        val router = createRouter<ModelKey>(mockk(), mockk())
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/testmodels")
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
        val response = client.get("/en/testmodels")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "list",
                TemplateResponseData(
                    "testmodels",
                    "en",
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
