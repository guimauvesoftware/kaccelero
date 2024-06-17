package dev.kaccelero.commons.repositories

import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.models.IContext
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class IListModelWithContextSuspendUseCaseTest {

    @Test
    fun testInvoke() = runBlocking {
        val useCase = object : IListModelWithContextSuspendUseCase<ModelTest> {
            override suspend fun invoke(input: IContext): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(listOf(ModelTest(1, "test")), useCase(Unit, mockk()))
    }

}
