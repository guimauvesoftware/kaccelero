package dev.kaccelero.serializers

import dev.kaccelero.models.UUID
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals

class UUIDSerializerTest {

    @Test
    fun testSerialize() {
        val uuid = UUID("7e6ae51b-4aab-4f5e-97f5-83ecb6dcf16f")
        val serialized = Serialization.json.encodeToString(uuid)
        assertEquals("\"7e6ae51b-4aab-4f5e-97f5-83ecb6dcf16f\"", serialized)
        val deserialized = Serialization.json.decodeFromString<UUID>(serialized)
        assertEquals(uuid, deserialized)
    }

}
