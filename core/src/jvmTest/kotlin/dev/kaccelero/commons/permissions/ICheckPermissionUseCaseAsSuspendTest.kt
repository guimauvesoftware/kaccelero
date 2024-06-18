package dev.kaccelero.commons.permissions

import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class ICheckPermissionUseCaseAsSuspendTest {

    @Test
    fun invokeSuspendFromNonSuspend() = runBlocking {
        val useCase = mockk<ICheckPermissionUseCase>()
        val permitee = object : IPermittee {}
        val permission = object : IPermission {}
        every { useCase(permitee, permission) } returns true
        val suspendUseCase = ICheckPermissionUseCaseAsSuspend(useCase)
        assertEquals(true, suspendUseCase(permitee, permission))
        coVerify { useCase(permitee, permission) }
    }

}
