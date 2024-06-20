package dev.kaccelero.mocks

import kotlinx.serialization.Serializable

@Serializable
data class TemplateResponseData<Model, Keys>(
    val route: String,
    val locale: String? = null,
    val keys: List<Keys>? = null,
    val item: Model? = null,
    val itemString: String? = null,
    val itemMap: String? = null,
    val itemModel: TestUser? = null,
    val items: List<Model>? = null,
    val code: Int? = null,
    val error: String? = null,
)
