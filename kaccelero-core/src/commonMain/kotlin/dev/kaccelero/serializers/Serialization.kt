package dev.kaccelero.serializers

import kotlinx.serialization.json.Json

object Serialization {

    val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

}
