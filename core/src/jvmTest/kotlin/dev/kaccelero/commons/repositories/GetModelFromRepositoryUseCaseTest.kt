package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import dev.kaccelero.repositories.IModelRepository
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class GetModelFromRepositoryUseCaseTest {

    @Test
    fun testInvoke() {
        val repository = mockk<IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest>>()
        val useCase = GetModelFromRepositoryUseCase(repository)
        every { repository.get(1, Unit) } returns ModelTest(1, "test")
        assertEquals(ModelTest(1, "test"), useCase(1))
    }

}
