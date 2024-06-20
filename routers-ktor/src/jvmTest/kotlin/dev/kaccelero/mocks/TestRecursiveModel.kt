package dev.kaccelero.mocks

import dev.kaccelero.annotations.Schema
import kotlinx.serialization.Serializable

@Serializable
data class TestRecursiveModel(
    @Schema("Name", "abc")
    val name: String,
    @Schema("Children", "[]")
    val children: List<TestRecursiveModel>? = null,
)
