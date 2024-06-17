package dev.kaccelero.mocks

import dev.kaccelero.annotations.ModelProperty
import dev.kaccelero.annotations.Schema
import dev.kaccelero.models.IModel
import kotlinx.serialization.Serializable

@Serializable
data class TestModel(
    @ModelProperty("id") @Schema("ID", "123")
    override val id: Long,
    @ModelProperty("string") @Schema("String", "abc")
    val string: String,
) : IModel<Long, TestCreatePayload, TestUpdatePayload>
