package dev.kaccelero.commons.localization

import kotlin.test.Test
import kotlin.test.assertEquals

class ITranslateUseCaseTest {

    @Test
    fun invokeWithoutParameters() {
        val useCase = object : ITranslateUseCase {
            override fun invoke(input1: Locale, input2: String, input3: List<String>): String =
                input1.language + ":" + input2 + ":" + input3.joinToString()
        }
        assertEquals("en:test:", useCase(Locale.ENGLISH, "test"))
    }

}
