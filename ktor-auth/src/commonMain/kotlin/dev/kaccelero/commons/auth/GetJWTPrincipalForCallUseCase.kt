package dev.kaccelero.commons.auth

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

class GetJWTPrincipalForCallUseCase : IGetJWTPrincipalForCallUseCase {

    override fun invoke(input: ApplicationCall): JWTPrincipal? = input.principal()

}
