package dev.kaccelero.commons.auth

import com.auth0.jwt.interfaces.RSAKeyProvider
import dev.kaccelero.serializers.Serialization
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

class AppleKeyProvider : RSAKeyProvider {

    private val appleKeysUrl = "https://appleid.apple.com/auth/keys"

    private var keys = listOf<AppleKey>()

    override fun getPublicKeyById(keyId: String?): RSAPublicKey? = keys.find { keyId == it.kid }
    override fun getPrivateKey(): RSAPrivateKey? = null
    override fun getPrivateKeyId(): String? = null

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Serialization.json)
        }
    }

    suspend fun fetchKeys() {
        keys = client.get(appleKeysUrl).body<AppleKeyPayload>().keys
    }

}
