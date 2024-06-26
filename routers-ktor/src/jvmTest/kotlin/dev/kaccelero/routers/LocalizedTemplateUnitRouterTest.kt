package dev.kaccelero.routers

import dev.kaccelero.annotations.ModelKey
import dev.kaccelero.commons.localization.IGetLocaleForCallUseCase
import dev.kaccelero.controllers.IUnitController
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
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.serialization.json.Json
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalizedTemplateUnitRouterTest {

    private fun installApp(application: ApplicationTestBuilder): HttpClient {
        application.install(I18n) {
            supportedLocales = listOf("en").map(Locale::forLanguageTag)
            useOfUri = true
        }
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
        getLocaleForCallUseCase: IGetLocaleForCallUseCase,
    ): LocalizedTemplateUnitRouter {
        return LocalizedTemplateUnitRouter(
            controller,
            ITestUnitController::class,
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
            getLocaleForCallUseCase
        )
    }

    @Test
    fun testRedirect() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestUnitController>()
        val getLocaleForCallUseCase = mockk<IGetLocaleForCallUseCase>()
        val router = createRouter<ModelKey>(controller, getLocaleForCallUseCase)
        coEvery { controller.hello() } returns "Hello world"
        every { getLocaleForCallUseCase(any()) } returns Locale.ENGLISH
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/hello")
        assertEquals(HttpStatusCode.Found, response.status)
    }

    @Test
    fun testLocaleEnglish() = testApplication {
        val client = installApp(this)
        val controller = mockk<ITestUnitController>()
        val getLocaleForCallUseCase = mockk<IGetLocaleForCallUseCase>()
        val router = createRouter<ModelKey>(controller, getLocaleForCallUseCase)
        coEvery { controller.hello() } returns "Hello world"
        every { getLocaleForCallUseCase(any()) } returns Locale.ENGLISH
        routing {
            router.createRoutes(this)
        }
        val response = client.get("/en/hello")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            TemplateResponse(
                "hello.ftl",
                TemplateResponseData<TestModel, ModelKey>(
                    "",
                    "en",
                    keys = listOf(),
                    itemString = "Hello world",
                )
            ), response.body()
        )
    }

}
