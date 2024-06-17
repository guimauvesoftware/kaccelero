package dev.kaccelero.mocks

import dev.kaccelero.models.UUID
import kotlinx.datetime.*

data class ConstructPayloadOptionalTest(
    val byte: Byte? = null,
    val uByte: UByte? = null,
    val short: Short? = null,
    val uShort: UShort? = null,
    val int: Int? = null,
    val uInt: UInt? = null,
    val long: Long? = null,
    val uLong: ULong? = null,
    val char: Char? = null,
    val float: Float? = null,
    val double: Double? = null,
    val uuid: UUID? = null,
    val string: String? = null,
    val instant: Instant? = null,
    val localDateTime: LocalDateTime? = null,
    val localDate: LocalDate? = null,
    val localTime: LocalTime? = null,
    val month: Month? = null,
    val dayOfWeek: DayOfWeek? = null,
)
