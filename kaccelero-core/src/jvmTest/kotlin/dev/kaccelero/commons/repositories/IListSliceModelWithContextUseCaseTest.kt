package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.models.IContext
import dev.kaccelero.repositories.Pagination
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class IListSliceModelWithContextUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : IListSliceModelWithContextUseCase<ModelTest> {
            override fun invoke(input1: Pagination, input2: IContext): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(listOf(ModelTest(1, "test")), useCase(Pagination(1, 0), Unit, mockk()))
    }

}
