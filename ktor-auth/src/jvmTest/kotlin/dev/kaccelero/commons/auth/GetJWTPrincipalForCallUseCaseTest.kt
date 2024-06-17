package dev.kaccelero.commons.auth

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class GetJWTPrincipalForCallUseCaseTest {

    @Test
    fun invoke() {
        val useCase = GetJWTPrincipalForCallUseCase()
        val call = mockk<ApplicationCall>()
        val principal = mockk<JWTPrincipal>()
        every { call.principal<JWTPrincipal>() } returns principal
        assertEquals(principal, useCase(call))
    }

    @Test
    fun invokeNull() {
        val useCase = GetJWTPrincipalForCallUseCase()
        val call = mockk<ApplicationCall>()
        every { call.principal<JWTPrincipal>() } returns null
        assertEquals(null, useCase(call))
    }

}
