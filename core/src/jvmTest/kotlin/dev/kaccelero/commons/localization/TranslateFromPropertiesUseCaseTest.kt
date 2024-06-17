package dev.kaccelero.commons.localization

import kotlin.test.Test
import kotlin.test.assertEquals

class TranslateFromPropertiesUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = TranslateFromPropertiesUseCase()
        assertEquals("Hello world!", useCase(Locale.ENGLISH, "hello_world"))
    }

    @Test
    fun testInvokeFrench() {
        val useCase = TranslateFromPropertiesUseCase()
        assertEquals("Coucou tout le monde !", useCase(Locale.FRENCH, "hello_world"))
    }

    @Test
    fun testInvokeArgs() {
        val useCase = TranslateFromPropertiesUseCase()
        assertEquals("Hello Nathan!", useCase(Locale.ENGLISH, "hello_arg", listOf("Nathan")))
    }

}
