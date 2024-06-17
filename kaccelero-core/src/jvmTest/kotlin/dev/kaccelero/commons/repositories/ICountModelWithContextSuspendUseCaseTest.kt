package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.models.IContext
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class ICountModelWithContextSuspendUseCaseTest {

    @Test
    fun testInvoke() = runBlocking {
        val useCase = object : ICountModelWithContextSuspendUseCase<ModelTest> {
            override suspend fun invoke(input: IContext): Long = 1
        }
        assertEquals(1, useCase(Unit, mockk()))
    }

}
