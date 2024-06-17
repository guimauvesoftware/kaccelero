package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import kotlin.test.Test
import kotlin.test.assertEquals

class IListModelUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : IListModelUseCase<ModelTest> {
            override fun invoke(): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(listOf(ModelTest(1, "test")), useCase(Unit))
    }

}
