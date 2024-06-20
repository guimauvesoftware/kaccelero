package dev.kaccelero.mocks

import dev.kaccelero.models.IChildModel
import kotlinx.serialization.Serializable

@Serializable
data class TestChildModel(
    override val id: Long,
    override val parentId: Long,
    val string: String,
) : IChildModel<Long, TestCreatePayload, TestUpdatePayload, Long>
