package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import dev.kaccelero.models.IContext
import dev.kaccelero.repositories.IModelRepository
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class DeleteModelWithContextFromRepositoryUseCaseTest {

    @Test
    fun testInvoke() {
        val repository = mockk<IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest>>()
        val useCase = DeleteModelWithContextFromRepositoryUseCase(repository)
        val context = mockk<IContext>()
        every { repository.delete(1, Unit, context) } returns true
        assertEquals(true, useCase(1, context))
    }

}
