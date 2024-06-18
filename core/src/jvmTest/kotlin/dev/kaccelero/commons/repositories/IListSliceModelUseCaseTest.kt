package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.repositories.Pagination
import kotlin.test.Test
import kotlin.test.assertEquals

class IListSliceModelUseCaseTest {

    @Test
    fun testInvokeLimitOffset() {
        val useCase = object : IListSliceModelUseCase<ModelTest> {
            override fun invoke(input: Pagination): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(listOf(ModelTest(1, "test")), useCase(Pagination(1, 0), Unit))
    }

}
