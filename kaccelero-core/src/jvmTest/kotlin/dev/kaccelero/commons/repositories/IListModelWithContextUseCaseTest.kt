package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.models.IContext
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class IListModelWithContextUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : IListModelWithContextUseCase<ModelTest> {
            override fun invoke(input: IContext): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(listOf(ModelTest(1, "test")), useCase(Unit, mockk()))
    }

}
