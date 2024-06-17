package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import dev.kaccelero.repositories.IModelSuspendRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class CreateModelFromRepositorySuspendUseCaseTest {

    @Test
    fun testInvoke() = runBlocking {
        val repository = mockk<IModelSuspendRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest>>()
        val useCase = CreateModelFromRepositorySuspendUseCase(repository)
        coEvery {
            repository.create(CreatePayloadTest("test"), Unit)
        } returns ModelTest(1, "test")
        assertEquals(ModelTest(1, "test"), useCase(CreatePayloadTest("test")))
    }

}
