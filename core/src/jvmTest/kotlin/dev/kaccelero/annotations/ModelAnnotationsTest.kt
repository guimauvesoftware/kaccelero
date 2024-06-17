package dev.kaccelero.annotations

import dev.kaccelero.mocks.*
import dev.kaccelero.models.UUID
import kotlinx.datetime.*
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ModelAnnotationsTest {

    @Test
    fun testModelKeys() {
        assertEquals(
            listOf(
                ModelKey("id", "id", ""),
                ModelKey("value", "string", ""),
                ModelKey("check", "boolean", "checkbox")
            ),
            ModelAnnotations.modelKeys(AnnotatedModelTest::class)
        )
    }

    @Test
    fun testUpdatePayloadKeys() {
        assertEquals(
            listOf(
                PayloadKey("check", "boolean", "checkbox", false),
                PayloadKey("value", "string", "", true)
            ),
            ModelAnnotations.updatePayloadKeys(
                AnnotatedModelTest::class,
                AnnotatedUpdatePayloadTest::class
            )
        )
    }

    @Test
    fun testCreatePayloadKeys() {
        assertEquals(
            listOf(
                PayloadKey("value", "string", "", true)
            ),
            ModelAnnotations.createPayloadKeys(
                AnnotatedModelTest::class,
                AnnotatedCreatePayloadTest::class
            )
        )
    }

    @Test
    fun testConstructPayload() {
        val uuid = UUID()
        val now = Clock.System.now()
        assertEquals(
            ConstructPayloadTest(
                1,
                2u,
                3,
                4u,
                5,
                6u,
                7,
                8u,
                'a',
                9.0f,
                10.0,
                uuid,
                "hello world!",
                now,
                now.toLocalDateTime(TimeZone.UTC),
                now.toLocalDateTime(TimeZone.UTC).date,
                now.toLocalDateTime(TimeZone.UTC).time,
                Month.JANUARY,
                DayOfWeek.MONDAY
            ),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadTest::class,
                mapOf(
                    "byte" to "1",
                    "uByte" to "2",
                    "short" to "3",
                    "uShort" to "4",
                    "int" to "5",
                    "uInt" to "6",
                    "long" to "7",
                    "uLong" to "8",
                    "char" to "a",
                    "float" to "9.0",
                    "double" to "10.0",
                    "string" to "hello world!",
                    "uuid" to uuid.toString(),
                    "instant" to now.toString(),
                    "localDateTime" to now.toLocalDateTime(TimeZone.UTC).toString(),
                    "localDate" to now.toLocalDateTime(TimeZone.UTC).date.toString(),
                    "localTime" to now.toLocalDateTime(TimeZone.UTC).time.toString(),
                    "month" to Month.JANUARY.toString(),
                    "dayOfWeek" to DayOfWeek.MONDAY.toString()
                )
            )
        )
    }

    @Test
    fun testConstructPayloadOptionals() {
        val uuid = UUID()
        val now = Clock.System.now()
        assertEquals(
            ConstructPayloadOptionalTest(
                1,
                2u,
                3,
                4u,
                5,
                6u,
                7,
                8u,
                'a',
                9.0f,
                10.0,
                uuid,
                "hello world!",
                now,
                now.toLocalDateTime(TimeZone.UTC),
                now.toLocalDateTime(TimeZone.UTC).date,
                now.toLocalDateTime(TimeZone.UTC).time,
                Month.JANUARY,
                DayOfWeek.MONDAY
            ),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadOptionalTest::class,
                mapOf(
                    "byte" to "1",
                    "uByte" to "2",
                    "short" to "3",
                    "uShort" to "4",
                    "int" to "5",
                    "uInt" to "6",
                    "long" to "7",
                    "uLong" to "8",
                    "char" to "a",
                    "float" to "9.0",
                    "double" to "10.0",
                    "uuid" to uuid.toString(),
                    "string" to "hello world!",
                    "instant" to now.toString(),
                    "localDateTime" to now.toLocalDateTime(TimeZone.UTC).toString(),
                    "localDate" to now.toLocalDateTime(TimeZone.UTC).date.toString(),
                    "localTime" to now.toLocalDateTime(TimeZone.UTC).time.toString(),
                    "month" to Month.JANUARY.toString(),
                    "dayOfWeek" to DayOfWeek.MONDAY.toString()
                )
            )
        )
    }

    @Test
    fun testConstructPayloadLists() {
        val uuid = UUID()
        val now = Clock.System.now()
        assertEquals(
            ConstructPayloadListTest(
                listOf(1),
                listOf(2u),
                listOf(3),
                listOf(4u),
                listOf(5),
                listOf(6u),
                listOf(7),
                listOf(8u),
                listOf('a'),
                listOf(9.0f),
                listOf(10.0),
                listOf(uuid),
                listOf("hello world!"),
                listOf(now),
                listOf(now.toLocalDateTime(TimeZone.UTC)),
                listOf(now.toLocalDateTime(TimeZone.UTC).date),
                listOf(now.toLocalDateTime(TimeZone.UTC).time),
                listOf(Month.JANUARY),
                listOf(DayOfWeek.MONDAY)
            ),
            ModelAnnotations.constructPayloadFromStringLists(
                ConstructPayloadListTest::class,
                mapOf(
                    "byte" to listOf("1"),
                    "uByte" to listOf("2"),
                    "short" to listOf("3"),
                    "uShort" to listOf("4"),
                    "int" to listOf("5"),
                    "uInt" to listOf("6"),
                    "long" to listOf("7"),
                    "uLong" to listOf("8"),
                    "char" to listOf("a"),
                    "float" to listOf("9.0"),
                    "double" to listOf("10.0"),
                    "uuid" to listOf(uuid.toString()),
                    "string" to listOf("hello world!"),
                    "instant" to listOf(now.toString()),
                    "localDateTime" to listOf(now.toLocalDateTime(TimeZone.UTC).toString()),
                    "localDate" to listOf(now.toLocalDateTime(TimeZone.UTC).date.toString()),
                    "localTime" to listOf(now.toLocalDateTime(TimeZone.UTC).time.toString()),
                    "month" to listOf(Month.JANUARY.toString()),
                    "dayOfWeek" to listOf(DayOfWeek.MONDAY.toString())
                )
            )
        )
    }

    @Test
    fun testConstructPayloadOptionalsWithNulls() {
        assertEquals(
            ConstructPayloadOptionalTest(),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadOptionalTest::class,
                mapOf()
            )
        )
    }

    @Test
    fun testConstructPayloadMissingKeys() {
        assertEquals(
            null,
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadTest::class,
                mapOf()
            )
        )
    }

    @Test
    fun testConstructPayloadBooleanTrue() {
        assertEquals(
            ConstructPayloadBooleanTest(true),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadBooleanTest::class,
                mapOf("boolean" to "true")
            )
        )
    }

    @Test
    fun testConstructPayloadBooleanFalse() {
        assertEquals(
            ConstructPayloadBooleanTest(false),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadBooleanTest::class,
                mapOf("boolean" to "false")
            )
        )
    }

    @Test
    fun testConstructPayloadBooleanOn() {
        assertEquals(
            ConstructPayloadBooleanTest(true),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadBooleanTest::class,
                mapOf("boolean" to "on")
            )
        )
    }

    @Test
    fun testConstructPayloadBooleanOmitted() {
        assertEquals(
            ConstructPayloadBooleanTest(false),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadBooleanTest::class,
                mapOf()
            )
        )
    }

    @Test
    fun testConstructPayloadOptionalBooleanTrue() {
        assertEquals(
            ConstructPayloadOptionalBooleanTest(true),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadOptionalBooleanTest::class,
                mapOf("boolean" to "true")
            )
        )
    }

    @Test
    fun testConstructPayloadOptionalBooleanFalse() {
        assertEquals(
            ConstructPayloadOptionalBooleanTest(false),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadOptionalBooleanTest::class,
                mapOf("boolean" to "false")
            )
        )
    }

    @Test
    fun testConstructPayloadOptionalBooleanOn() {
        assertEquals(
            ConstructPayloadOptionalBooleanTest(true),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadOptionalBooleanTest::class,
                mapOf("boolean" to "on")
            )
        )
    }

    @Test
    fun testConstructPayloadOptionalBooleanOmitted() {
        assertEquals(
            ConstructPayloadOptionalBooleanTest(),
            ModelAnnotations.constructPayloadFromStrings(
                ConstructPayloadOptionalBooleanTest::class,
                mapOf()
            )
        )
    }

    @Test
    fun testConstructId() {
        assertEquals(
            1,
            ModelAnnotations.constructIdFromString(
                ModelTest::class,
                "1"
            )
        )
    }

    @Test
    fun testConstructIdInvalid() {
        val exception = assertFailsWith(IllegalArgumentException::class) {
            ModelAnnotations.constructIdFromString(
                InvalidModelTest::class,
                ""
            )
        }
        assertEquals("Unsupported id type: kotlin.Unit", exception.message)
    }

    @Test
    fun testConstructPrimitive() {
        assertEquals(
            1,
            ModelAnnotations.constructPrimitiveFromString(
                typeOf<Int>(),
                "1"
            )
        )
    }

    @Test
    fun testValidatePayload() {
        ModelAnnotations.validatePayload(ValidatedCreatePayload("hello"), ValidatedCreatePayload::class)
    }

    @Test
    fun testValidatePayloadFails() {
        val exception = assertFailsWith<PropertyValidatorException> {
            ModelAnnotations.validatePayload(ValidatedCreatePayload("h"), ValidatedCreatePayload::class)
        }
        assertEquals("name", exception.key)
        assertEquals("h", exception.value)
        assertEquals(StringPropertyValidator(minLength = 3), exception.validator)
        assertEquals("minLength", exception.reason)
    }

}
