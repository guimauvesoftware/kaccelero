package dev.kaccelero.mocks

import dev.kaccelero.models.IUser
import kotlinx.serialization.Serializable

@Serializable
data class TestUser(
    val id: String,
) : IUser
