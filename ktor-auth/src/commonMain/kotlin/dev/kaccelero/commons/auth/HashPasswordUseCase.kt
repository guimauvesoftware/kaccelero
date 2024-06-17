package dev.kaccelero.commons.auth

import at.favre.lib.crypto.bcrypt.BCrypt

class HashPasswordUseCase : IHashPasswordUseCase {

    override fun invoke(input: String): String = BCrypt.withDefaults().hashToString(12, input.toCharArray())

}
