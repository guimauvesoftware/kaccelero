package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetModelSuspendUseCaseTest {

    @Test
    fun testInvoke() = runBlocking {
        val useCase = object : IGetModelSuspendUseCase<ModelTest, Long> {
            override suspend fun invoke(input: Long): ModelTest = ModelTest(1, "test")
        }
        assertEquals(ModelTest(1, "test"), useCase(1, Unit))
    }

}
