package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class ICountModelSuspendUseCaseTest {

    @Test
    fun testInvoke() = runBlocking {
        val useCase = object : ICountModelSuspendUseCase<ModelTest> {
            override suspend fun invoke(): Long = 1
        }
        assertEquals(1, useCase(Unit))
    }

}
