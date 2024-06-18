package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.models.IContext
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class IDeleteModelWithContextUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : IDeleteModelWithContextUseCase<ModelTest, Long> {
            override fun invoke(input1: Long, input2: IContext): Boolean = true
        }
        assertEquals(true, useCase(1, Unit, mockk()))
    }

}
