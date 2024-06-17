package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import dev.kaccelero.repositories.IModelSuspendRepository
import dev.kaccelero.repositories.Pagination
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class ListSliceModelFromRepositorySuspendUseCaseTest {

    @Test
    fun testInvokeLimitOffset() = runBlocking {
        val repository = mockk<IModelSuspendRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest>>()
        val useCase = ListSliceModelFromRepositorySuspendUseCase(repository)
        coEvery { repository.list(Pagination(1, 0), Unit) } returns listOf(ModelTest(1, "test"))
        assertEquals(listOf(ModelTest(1, "test")), useCase(Pagination(1, 0)))
    }

}
