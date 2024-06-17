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

class CreateModelWithContextFromRepositoryUseCaseTest {

    @Test
    fun testInvoke() {
        val repository = mockk<IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest>>()
        val useCase = CreateModelWithContextFromRepositoryUseCase(repository)
        val context = mockk<IContext>()
        every {
            repository.create(CreatePayloadTest("test"), Unit, context)
        } returns ModelTest(1, "test")
        assertEquals(ModelTest(1, "test"), useCase(CreatePayloadTest("test"), context))
    }

}
