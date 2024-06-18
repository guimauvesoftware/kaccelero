package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.models.IContext
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetModelWithContextUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : IGetModelWithContextUseCase<ModelTest, Long> {
            override fun invoke(input1: Long, input2: IContext): ModelTest = ModelTest(1, "test")
        }
        assertEquals(ModelTest(1, "test"), useCase(1, Unit, mockk()))
    }

}
