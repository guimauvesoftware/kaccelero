package dev.kaccelero.commons.localization

import dev.kaccelero.plugins.I18n
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class GetLocaleForCallUseCaseTest {

    private fun installApp(
        application: ApplicationTestBuilder,
        useOfCookie: Boolean = false,
        useOfUri: Boolean = false,
    ): HttpClient {
        application.install(I18n) {
            this.supportedLocales = listOf("en", "fr").map(Locale::forLanguageTag)
            this.useOfCookie = useOfCookie
            this.useOfUri = useOfUri
        }
        return application.createClient {
            followRedirects = false
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json(kotlinx.serialization.json.Json)
            }
            install(HttpCookies)
        }
    }

    @Test
    fun testLocaleDefault() = testApplication {
        val client = installApp(this)
        routing {
            get {
                call.respond(GetLocaleForCallUseCase()(call).language)
            }
        }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("en", response.body())
    }

    @Test
    fun testLocaleDefaultBecauseDisabled() = testApplication {
        val client = installApp(this)
        routing {
            get("/{locale}/home") {
                call.respond(GetLocaleForCallUseCase()(call).language)
            }
        }
        val response = client.get("/fr/home") {
            cookie("locale", "fr")
        }
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("en", response.body())
    }

    @Test
    fun testLocaleAcceptHeader() = testApplication {
        val client = installApp(this)
        routing {
            get {
                call.respond(GetLocaleForCallUseCase()(call).language)
            }
        }
        val response = client.get("/") {
            header(HttpHeaders.AcceptLanguage, "fr")
        }
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("fr", response.body())
    }

    @Test
    fun testLocaleUri() = testApplication {
        val client = installApp(this, useOfUri = true)
        routing {
            get("/{locale}/home") {
                call.respond(GetLocaleForCallUseCase()(call).language)
            }
        }
        val response = client.get("/fr/home")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("fr", response.body())
    }

    @Test
    fun testLocaleCookie() = testApplication {
        val client = installApp(this, useOfCookie = true)
        routing {
            get {
                call.respond(GetLocaleForCallUseCase()(call).language)
            }
        }
        val response = client.get("/") {
            cookie("locale", "fr")
        }
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("fr", response.body())
    }

    @Test
    fun testLocaleCookieDisabled() = testApplication {
        val client = installApp(this, useOfCookie = false)
        routing {
            get {
                call.respond(GetLocaleForCallUseCase()(call).language)
            }
        }
        val response = client.get("/") {
            cookie("locale", "fr")
        }
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("en", response.body())
    }

    @Test
    fun testLocaleCookieWritten() = testApplication {
        val client = installApp(this, useOfCookie = true)
        routing {
            get {
                call.respond(GetLocaleForCallUseCase()(call).language)
            }
        }
        val response = client.get("/") {
            header(HttpHeaders.AcceptLanguage, "fr")
        }
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("fr", response.body())
        val cookie = client.cookies("http://localhost").first()
        assertEquals("locale", cookie.name)
        assertEquals("fr", cookie.value)
    }


    @Test
    fun testLocaleUriCookieWritten() = testApplication {
        val client = installApp(this, useOfCookie = true, useOfUri = true)
        routing {
            get("/{locale}/home") {
                call.respond(GetLocaleForCallUseCase()(call).language)
            }
        }
        val response = client.get("/fr/home")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("fr", response.body())
        val cookie = client.cookies("http://localhost/fr/home").first()
        assertEquals("locale", cookie.name)
        assertEquals("fr", cookie.value)
    }

}
