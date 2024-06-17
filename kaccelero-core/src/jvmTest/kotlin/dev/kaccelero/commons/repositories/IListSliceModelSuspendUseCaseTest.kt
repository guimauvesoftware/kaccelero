package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.repositories.Pagination
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class IListSliceModelSuspendUseCaseTest {

    @Test
    fun testInvokeLimitOffset() = runBlocking {
        val useCase = object : IListSliceModelSuspendUseCase<ModelTest> {
            override suspend fun invoke(input: Pagination): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(listOf(ModelTest(1, "test")), useCase(Pagination(1, 0), Unit))
    }

}
