package dev.kaccelero.commons.auth

import dev.kaccelero.usecases.IUseCase
import io.ktor.server.application.*
import io.ktor.server.auth.jwt.*

interface IGetJWTPrincipalForCallUseCase : IUseCase<ApplicationCall, JWTPrincipal?>
