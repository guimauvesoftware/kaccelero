package dev.kaccelero.plugins

import io.ktor.server.testing.*
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class I18nTest {

    @Test
    fun throwsWhenNoLocale() {
        assertFailsWith(UninitializedPropertyAccessException::class) {
            testApplication {
                install(I18n)
            }
        }
    }

    @Test
    fun throwsWhenLocalesEmpty() {
        assertFailsWith(IllegalArgumentException::class) {
            testApplication {
                install(I18n) { supportedLocales = listOf() }
            }
        }
    }

    @Test
    fun throwsWhenDefaultNotSupported() {
        assertFailsWith(IllegalArgumentException::class) {
            testApplication {
                install(I18n) {
                    supportedLocales = listOf("en", "fr").map(Locale::forLanguageTag)
                    defaultLocale = Locale.forLanguageTag("es")
                }
            }
        }
    }

    @Test
    fun defaultIsFirstLocale(): Unit = testApplication {
        val locales = listOf("en", "fr").map(Locale::forLanguageTag)
        install(I18n) {
            supportedLocales = locales
        }
        application {
            assertEquals(locales.first(), i18n.defaultLocale)
        }
    }

}
