package dev.kaccelero.commons.users

import dev.kaccelero.commons.exceptions.ControllerException
import dev.kaccelero.models.IUser
import io.ktor.http.*
import io.ktor.server.application.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RequireUserForCallUseCaseTest {

    @Test
    fun invoke() = runBlocking {
        val getUserForCallUseCase = mockk<IGetUserForCallUseCase>()
        val useCase = RequireUserForCallUseCase(getUserForCallUseCase)
        val call = mockk<ApplicationCall>()
        val user = object : IUser {}
        coEvery { getUserForCallUseCase(call) } returns user
        assertEquals(user, useCase(call))
    }

    @Test
    fun invokeFails() = runBlocking {
        val getUserForCallUseCase = mockk<IGetUserForCallUseCase>()
        val useCase = RequireUserForCallUseCase(getUserForCallUseCase)
        val call = mockk<ApplicationCall>()
        coEvery { getUserForCallUseCase(call) } returns null
        val exception = assertFailsWith(ControllerException::class) {
            useCase(call)
        }
        assertEquals(HttpStatusCode.Unauthorized, exception.code)
        assertEquals("auth_invalid_credentials", exception.key)
    }

}
