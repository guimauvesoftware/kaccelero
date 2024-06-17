package dev.kaccelero.mocks

import kotlinx.serialization.Serializable

@Serializable
data class TemplateResponseData<Model, Keys>(
    val locale: String,
    val route: String,
    val keys: List<Keys>? = null,
    val item: Model? = null,
    val itemString: String? = null,
    val itemMap: String? = null,
    val items: List<Model>? = null,
    val code: Int? = null,
    val error: String? = null,
)
