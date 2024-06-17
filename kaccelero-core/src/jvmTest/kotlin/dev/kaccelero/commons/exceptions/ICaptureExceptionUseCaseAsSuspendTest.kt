package dev.kaccelero.commons.exceptions

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class ICaptureExceptionUseCaseAsSuspendTest {

    @Test
    fun invokeSuspendFromNonSuspend() = runBlocking {
        val useCase = mockk<ICaptureExceptionUseCase>()
        val suspendUseCase = ICaptureExceptionUseCaseAsSuspend(useCase)
        val exception = Exception("test")
        every { useCase(exception) } returns Unit
        suspendUseCase(exception)
        verify { useCase(exception) }
    }

}
