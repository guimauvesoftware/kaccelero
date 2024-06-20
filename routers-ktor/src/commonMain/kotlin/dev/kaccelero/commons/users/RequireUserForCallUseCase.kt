package dev.kaccelero.commons.users

import dev.kaccelero.commons.exceptions.ControllerException
import dev.kaccelero.models.IUser
import io.ktor.http.*
import io.ktor.server.application.*

class RequireUserForCallUseCase(
    private val getUserForCallUseCase: IGetUserForCallUseCase,
) : IRequireUserForCallUseCase {

    override suspend fun invoke(input: ApplicationCall): IUser =
        getUserForCallUseCase(input) ?: throw ControllerException(
            HttpStatusCode.Unauthorized, "auth_invalid_credentials"
        )

}
