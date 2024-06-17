package dev.kaccelero.mocks

import dev.kaccelero.models.IModel

data class InvalidModelTest(
    override val id: Unit,
) : IModel<Unit, Unit, Unit>
