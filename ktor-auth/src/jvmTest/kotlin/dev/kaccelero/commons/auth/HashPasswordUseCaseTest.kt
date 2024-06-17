package dev.kaccelero.commons.auth

import at.favre.lib.crypto.bcrypt.BCrypt
import kotlin.test.Test
import kotlin.test.assertTrue

class HashPasswordUseCaseTest {

    @Test
    fun invoke() {
        val useCase = HashPasswordUseCase()
        val hash = useCase("password")
        assertTrue(BCrypt.verifyer().verify("password".toCharArray(), hash).verified)
    }

}
