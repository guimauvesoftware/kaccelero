package dev.kaccelero.commons.auth

import at.favre.lib.crypto.bcrypt.BCrypt

class VerifyPasswordUseCase : IVerifyPasswordUseCase {

    override fun invoke(input: VerifyPasswordPayload): Boolean =
        BCrypt.verifyer().verify(input.password.toCharArray(), input.hash).verified

}
