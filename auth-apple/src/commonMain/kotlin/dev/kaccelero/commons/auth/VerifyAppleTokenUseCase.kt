package dev.kaccelero.commons.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT

class VerifyAppleTokenUseCase(
    private val bundleId: String,
) : IVerifyAppleTokenUseCase {

    private val appleKeyProvider = AppleKeyProvider()

    override suspend fun invoke(input: AppleToken): DecodedJWT? =
        try {
            appleKeyProvider.fetchKeys() // Always update, in case of key rotation
            JWT.require(Algorithm.RSA256(appleKeyProvider))
                .withIssuer("https://appleid.apple.com")
                .withAudience(bundleId)
                .withSubject(input.appleId)
                .build()
                .verify(input.token)
        } catch (e: JWTVerificationException) {
            null
        }

}
