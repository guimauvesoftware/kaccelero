package dev.kaccelero.mocks

import dev.kaccelero.models.IModel

data class ModelTest(
    override val id: Long,
    val value: String,
) : IModel<Long, CreatePayloadTest, UpdatePayloadTest>
