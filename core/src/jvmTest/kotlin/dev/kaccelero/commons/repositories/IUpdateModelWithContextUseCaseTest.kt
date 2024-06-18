package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import dev.kaccelero.models.IContext
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class IUpdateModelWithContextUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : IUpdateModelWithContextUseCase<ModelTest, Long, UpdatePayloadTest> {
            override fun invoke(input1: Long, input2: UpdatePayloadTest, input3: IContext): ModelTest =
                ModelTest(1, "test")
        }
        assertEquals(ModelTest(1, "test"), useCase(1, UpdatePayloadTest("test"), Unit, mockk()))
    }

}
