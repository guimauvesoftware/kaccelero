package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import kotlin.test.Test
import kotlin.test.assertEquals

class IUpdateModelUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : IUpdateModelUseCase<ModelTest, Long, UpdatePayloadTest> {
            override fun invoke(input1: Long, input2: UpdatePayloadTest): ModelTest = ModelTest(1, "test")
        }
        assertEquals(ModelTest(1, "test"), useCase(1, UpdatePayloadTest("test"), Unit))
    }

}
