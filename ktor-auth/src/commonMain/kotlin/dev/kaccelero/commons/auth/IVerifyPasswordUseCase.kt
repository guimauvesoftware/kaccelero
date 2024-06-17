package dev.kaccelero.commons.auth

import dev.kaccelero.usecases.IUseCase

interface IVerifyPasswordUseCase : IUseCase<VerifyPasswordPayload, Boolean>
