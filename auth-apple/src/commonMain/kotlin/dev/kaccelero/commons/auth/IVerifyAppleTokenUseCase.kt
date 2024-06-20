package dev.kaccelero.commons.auth

import com.auth0.jwt.interfaces.DecodedJWT
import dev.kaccelero.usecases.ISuspendUseCase

interface IVerifyAppleTokenUseCase : ISuspendUseCase<AppleToken, DecodedJWT?>
