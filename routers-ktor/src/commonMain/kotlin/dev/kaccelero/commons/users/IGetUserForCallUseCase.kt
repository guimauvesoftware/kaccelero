package dev.kaccelero.commons.users

import dev.kaccelero.models.IUser
import dev.kaccelero.usecases.ISuspendUseCase
import io.ktor.server.application.*

interface IGetUserForCallUseCase : ISuspendUseCase<ApplicationCall, IUser?>
