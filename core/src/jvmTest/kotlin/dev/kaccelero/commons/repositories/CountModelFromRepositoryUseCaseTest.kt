package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import dev.kaccelero.repositories.IModelRepository
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class CountModelFromRepositoryUseCaseTest {

    @Test
    fun testInvoke() {
        val repository = mockk<IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest>>()
        val useCase = CountModelFromRepositoryUseCase(repository)
        every { repository.count(Unit) } returns 1
        assertEquals(1, useCase())
    }

}
