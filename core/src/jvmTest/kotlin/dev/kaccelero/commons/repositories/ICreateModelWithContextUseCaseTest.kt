package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.models.IContext
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class ICreateModelWithContextUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : ICreateModelWithContextUseCase<ModelTest, CreatePayloadTest> {
            override fun invoke(input1: CreatePayloadTest, input2: IContext): ModelTest = ModelTest(1, "test")
        }
        assertEquals(ModelTest(1, "test"), useCase(CreatePayloadTest("test"), Unit, mockk()))
    }

}
