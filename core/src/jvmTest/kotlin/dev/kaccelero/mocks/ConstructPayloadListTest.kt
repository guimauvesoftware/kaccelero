package dev.kaccelero.mocks

import dev.kaccelero.models.UUID
import kotlinx.datetime.*

data class ConstructPayloadListTest(
    val byte: List<Byte>,
    val uByte: List<UByte>,
    val short: List<Short>,
    val uShort: List<UShort>,
    val int: List<Int>,
    val uInt: List<UInt>,
    val long: List<Long>,
    val uLong: List<ULong>,
    val char: List<Char>,
    val float: List<Float>,
    val double: List<Double>,
    val uuid: List<UUID>,
    val string: List<String>,
    val instant: List<Instant>,
    val localDateTime: List<LocalDateTime>,
    val localDate: List<LocalDate>,
    val localTime: List<LocalTime>,
    val month: List<Month>,
    val dayOfWeek: List<DayOfWeek>,
)
