package dev.kaccelero.mocks

import dev.kaccelero.annotations.ModelProperty
import dev.kaccelero.models.IModel
import kotlinx.serialization.Serializable

@Serializable
data class TestModel(
    @ModelProperty("id")
    override val id: Long,
    @ModelProperty("string")
    val string: String,
) : IModel<Long, TestCreatePayload, TestUpdatePayload>
