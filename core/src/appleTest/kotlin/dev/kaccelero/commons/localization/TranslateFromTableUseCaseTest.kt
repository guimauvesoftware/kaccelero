package dev.kaccelero.commons.localization

import platform.Foundation.currentLocale
import kotlin.test.Test
import kotlin.test.assertEquals

class TranslateFromTableUseCaseTest {

    @Test
    fun testLocalizeWithArgs() {
        val useCase = TranslateFromTableUseCase()
        assertEquals(
            "Hello!",
            useCase(Locale.currentLocale(), "Hello!", listOf())
        )
        assertEquals(
            "Hello, World!",
            useCase(Locale.currentLocale(), "Hello, %@!", listOf("World"))
        )
        assertEquals(
            "Hello, World!",
            useCase(Locale.currentLocale(), "%@, %@!", listOf("Hello", "World"))
        )
    }

}
