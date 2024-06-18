package dev.kaccelero.plugins

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class HealthTest {

    private fun installApp(
        application: ApplicationTestBuilder,
        configure: Health.Configuration.() -> Unit = {},
    ): HttpClient {
        application.application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
                json(Json)
            }
            install(Health, configure)
        }
        return application.createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json(Json)
            }
        }
    }

    @Test
    fun testHealthzNoCheck() = testApplication {
        val client = installApp(this)
        val response = client.get("/healthz")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(emptyMap<String, Boolean>(), response.body())
    }

    @Test
    fun testReadyzNoCheck() = testApplication {
        val client = installApp(this)
        val response = client.get("/readyz")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(emptyMap<String, Boolean>(), response.body())
    }

    @Test
    fun testHealthzDisabled() = testApplication {
        val client = installApp(this) {
            disableHealthCheck()
        }
        val response = client.get("/healthz")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun testReadyzDisabled() = testApplication {
        val client = installApp(this) {
            disableReadyCheck()
        }
        val response = client.get("/readyz")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun testHealthzCheckSuccess() = testApplication {
        val client = installApp(this) {
            healthCheck("test") { true }
        }
        val response = client.get("/healthz")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(mapOf("test" to true), response.body())
    }

    @Test
    fun testHealthzCheckFailure() = testApplication {
        val client = installApp(this) {
            healthCheck("test") { false }
        }
        val response = client.get("/healthz")
        assertEquals(HttpStatusCode.ServiceUnavailable, response.status)
        assertEquals(mapOf("test" to false), response.body())
    }

    @Test
    fun testReadyzCheckSuccess() = testApplication {
        val client = installApp(this) {
            readyCheck("test") { true }
        }
        val response = client.get("/readyz")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(mapOf("test" to true), response.body())
    }

    @Test
    fun testReadyzCheckFailure() = testApplication {
        val client = installApp(this) {
            readyCheck("test") { false }
        }
        val response = client.get("/readyz")
        assertEquals(HttpStatusCode.ServiceUnavailable, response.status)
        assertEquals(mapOf("test" to false), response.body())
    }

    @Test
    fun testCustomSuccess() = testApplication {
        val client = installApp(this) {
            customCheck("/smoketest", "test") { true }
        }
        val response = client.get("/smoketest")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(mapOf("test" to true), response.body())
    }

    @Test
    fun testCustomFailure() = testApplication {
        val client = installApp(this) {
            customCheck("/smoketest", "test") { false }
        }
        val response = client.get("/smoketest")
        assertEquals(HttpStatusCode.ServiceUnavailable, response.status)
        assertEquals(mapOf("test" to false), response.body())
    }

}
