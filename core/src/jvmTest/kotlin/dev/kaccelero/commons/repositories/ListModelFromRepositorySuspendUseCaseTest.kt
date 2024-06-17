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

class ListModelFromRepositorySuspendUseCaseTest {

    @Test
    fun testInvoke() = runBlocking {
        val repository = mockk<IModelSuspendRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest>>()
        val useCase = ListModelFromRepositorySuspendUseCase(repository)
        coEvery { repository.list(Unit) } returns listOf(ModelTest(1, "test"))
        assertEquals(listOf(ModelTest(1, "test")), useCase())
    }

}
