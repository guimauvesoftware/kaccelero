package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.models.IContext
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class ICreateModelWithContextSuspendUseCaseTest {

    @Test
    fun testInvoke() = runBlocking {
        val useCase = object : ICreateModelWithContextSuspendUseCase<ModelTest, CreatePayloadTest> {
            override suspend fun invoke(input1: CreatePayloadTest, input2: IContext): ModelTest = ModelTest(1, "test")
        }
        assertEquals(ModelTest(1, "test"), useCase(CreatePayloadTest("test"), Unit, mockk()))
    }

}
