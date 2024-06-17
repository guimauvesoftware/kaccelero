package dev.kaccelero.models

import kotlin.test.Test
import kotlin.test.assertEquals

class UUIDTest {

    @Test
    fun randomUUID() {
        val uuid = UUID()
        assertEquals(36, uuid.toString().length)
        assertEquals(4, uuid.toString().count { it == '-' })
        assertEquals(uuid, uuid)
    }

    @Test
    fun fromString() {
        val uuid = UUID("7e6ae51b-4aab-4f5e-97f5-83ecb6dcf16f")
        assertEquals("7e6ae51b-4aab-4f5e-97f5-83ecb6dcf16f", uuid.toString().lowercase())
    }

}
