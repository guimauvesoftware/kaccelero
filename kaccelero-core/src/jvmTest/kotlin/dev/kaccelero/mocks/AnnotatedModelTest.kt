package dev.kaccelero.mocks

import dev.kaccelero.annotations.ModelProperty
import dev.kaccelero.models.IModel

data class AnnotatedModelTest(
    @ModelProperty("id")
    override val id: Long,
    @ModelProperty("string")
    val value: String,
    @ModelProperty("boolean", "checkbox", true)
    val check: Boolean,
    @RandomAnnotation
    val ignored: String,
) : IModel<Long, AnnotatedCreatePayloadTest, AnnotatedUpdatePayloadTest>
