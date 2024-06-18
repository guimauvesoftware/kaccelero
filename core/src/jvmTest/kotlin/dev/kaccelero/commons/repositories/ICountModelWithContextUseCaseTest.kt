package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.models.IContext
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class ICountModelWithContextUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : ICountModelWithContextUseCase<ModelTest> {
            override fun invoke(input: IContext): Long = 1
        }
        assertEquals(1, useCase(Unit, mockk()))
    }

}
