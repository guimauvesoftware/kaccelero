package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class IListModelSuspendUseCaseTest {

    @Test
    fun testInvoke() = runBlocking {
        val useCase = object : IListModelSuspendUseCase<ModelTest> {
            override suspend fun invoke(): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(listOf(ModelTest(1, "test")), useCase(Unit))
    }

}
