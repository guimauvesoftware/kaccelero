package dev.kaccelero.commons.auth

import kotlinx.serialization.Serializable
import java.math.BigInteger
import java.security.interfaces.RSAPublicKey
import java.util.*

@Serializable
data class AppleKey(
    val kty: String,
    val kid: String,
    val use: String,
    val alg: String,
    val n: String,
    val e: String,
) : RSAPublicKey {

    override fun getAlgorithm() = kty

    override fun getFormat(): String? = null

    override fun getEncoded(): ByteArray? = null

    override fun getModulus() = BigInteger(
        1, Base64.getUrlDecoder().decode(n)
    )

    override fun getPublicExponent() = BigInteger(
        1, Base64.getUrlDecoder().decode(e)
    )

}
