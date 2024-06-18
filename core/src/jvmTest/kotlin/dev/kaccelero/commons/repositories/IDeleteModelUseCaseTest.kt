package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import kotlin.test.Test
import kotlin.test.assertEquals

class IDeleteModelUseCaseTest {

    @Test
    fun testInvoke() {
        val useCase = object : IDeleteModelUseCase<ModelTest, Long> {
            override fun invoke(input: Long): Boolean = true
        }
        assertEquals(true, useCase(1, Unit))
    }

}
