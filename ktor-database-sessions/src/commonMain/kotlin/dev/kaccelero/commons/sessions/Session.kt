package dev.kaccelero.commons.sessions

import dev.kaccelero.models.IModel

data class Session(
    override val id: String,
    val value: String,
) : IModel<String, Session, String>
