package dev.kaccelero.annotations

import kotlin.jvm.JvmStatic

object PropertyValidator {

    @JvmStatic
    fun validate(key: String, value: Any, validator: Any) {
        when (validator) {
            is StringPropertyValidator -> {
                if (value !is String) throw PropertyValidatorException(key, value, validator, "type")
                if (!value.matches(Regex(validator.regex))) throw PropertyValidatorException(
                    key,
                    value,
                    validator,
                    "regex"
                )
                if (value.length < validator.minLength) throw PropertyValidatorException(
                    key,
                    value,
                    validator,
                    "minLength"
                )
                if (value.length > validator.maxLength) throw PropertyValidatorException(
                    key,
                    value,
                    validator,
                    "maxLength"
                )
            }
        }
    }

}
