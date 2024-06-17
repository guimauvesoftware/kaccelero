package dev.kaccelero.mocks

import kotlinx.serialization.Serializable

@Serializable
data class TemplateResponse<Model, Keys>(
    val template: String,
    val data: TemplateResponseData<Model, Keys>,
)
