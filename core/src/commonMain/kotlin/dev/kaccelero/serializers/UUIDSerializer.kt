package dev.kaccelero.serializers

import dev.kaccelero.models.UUID
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class UUIDSerializer : KSerializer<UUID> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("dev.kaccelero.models.UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID = UUID(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: UUID): Unit = encoder.encodeString(value.toString())

}
