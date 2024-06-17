package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ICreateModelUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : ICreateModelUseCase<ModelTest, CreatePayloadTest> {
            override fun invoke(input: CreatePayloadTest): ModelTest = ModelTest(1, "test")
        }
        assertEquals(ModelTest(1, "test"), useCase(CreatePayloadTest("test"), Unit))
    }

}
