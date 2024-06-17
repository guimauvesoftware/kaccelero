package dev.kaccelero.annotations

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PropertyValidatorTest {

    @Test
    fun testValidateStringDefault() {
        PropertyValidator.validate("key", "test", StringPropertyValidator())
    }

    @Test
    fun testValidateStringRegexMatching() {
        PropertyValidator.validate("key", "abc123", StringPropertyValidator(regex = "[a-z]+[0-9]+"))
    }

    @Test
    fun testValidateStringRegexNotMatching() {
        val exception = assertFailsWith<PropertyValidatorException> {
            PropertyValidator.validate("key", "123abc", StringPropertyValidator(regex = "[a-z]+[0-9]+"))
        }
        assertEquals("key", exception.key)
        assertEquals("123abc", exception.value)
        assertEquals(StringPropertyValidator(regex = "[a-z]+[0-9]+"), exception.validator)
        assertEquals("regex", exception.reason)
    }

    @Test
    fun testValidateStringMinLengthMatching() {
        PropertyValidator.validate("key", "test", StringPropertyValidator(minLength = 4))
    }

    @Test
    fun testValidateStringMinLengthNotMatching() {
        val exception = assertFailsWith<PropertyValidatorException> {
            PropertyValidator.validate(
                "key",
                "test",
                StringPropertyValidator(minLength = 5)
            )
        }
        assertEquals("key", exception.key)
        assertEquals("test", exception.value)
        assertEquals(StringPropertyValidator(minLength = 5), exception.validator)
        assertEquals("minLength", exception.reason)
    }

    @Test
    fun testValidateStringMaxLengthMatching() {
        PropertyValidator.validate("key", "test", StringPropertyValidator(maxLength = 4))
    }

    @Test
    fun testValidateStringMaxLengthNotMatching() {
        val exception = assertFailsWith<PropertyValidatorException> {
            PropertyValidator.validate(
                "key",
                "test",
                StringPropertyValidator(maxLength = 3)
            )
        }
        assertEquals("key", exception.key)
        assertEquals("test", exception.value)
        assertEquals(StringPropertyValidator(maxLength = 3), exception.validator)
        assertEquals("maxLength", exception.reason)
    }

}
