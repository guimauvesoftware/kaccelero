package dev.kaccelero.commons.auth

import at.favre.lib.crypto.bcrypt.BCrypt
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VerifyPasswordUseCaseTest {

    @Test
    fun invoke() {
        val useCase = VerifyPasswordUseCase()
        assertTrue(
            useCase(
                VerifyPasswordPayload(
                    "password",
                    BCrypt.withDefaults().hashToString(12, "password".toCharArray())
                )
            )
        )
    }

    @Test
    fun invokeBad() {
        val useCase = VerifyPasswordUseCase()
        assertFalse(
            useCase(
                VerifyPasswordPayload(
                    "incorrect",
                    BCrypt.withDefaults().hashToString(12, "password".toCharArray())
                )
            )
        )
    }

}
