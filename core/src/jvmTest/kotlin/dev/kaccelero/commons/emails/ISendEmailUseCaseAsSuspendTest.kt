package dev.kaccelero.commons.emails

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class ISendEmailUseCaseAsSuspendTest {

    @Test
    fun invokeSuspendFromNonSuspend() = runBlocking {
        val useCase = mockk<ISendEmailUseCase>()
        val email = mockk<IEmail>()
        val recipients = listOf("recipient")
        val suspendUseCase = ISendEmailUseCaseAsSuspend(useCase)
        every { useCase(email, recipients) } returns Unit
        suspendUseCase(email, recipients)
        verify { useCase(email, recipients) }
    }

}
