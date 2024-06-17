package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import dev.kaccelero.repositories.IModelRepository
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class UpdateModelFromRepositoryUseCaseTest {

    @Test
    fun testInvoke() {
        val repository = mockk<IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest>>()
        val useCase = UpdateModelFromRepositoryUseCase(repository)
        every { repository.update(1, UpdatePayloadTest("test"), Unit) } returns true
        every { repository.get(1, Unit) } returns ModelTest(1, "test")
        assertEquals(ModelTest(1, "test"), useCase(1, UpdatePayloadTest("test")))
    }

    @Test
    fun testInvokeFails() {
        val repository = mockk<IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest>>()
        val useCase = UpdateModelFromRepositoryUseCase(repository)
        every { repository.update(1, UpdatePayloadTest("test"), Unit) } returns false
        assertEquals(null, useCase(1, UpdatePayloadTest("test")))
    }

}
