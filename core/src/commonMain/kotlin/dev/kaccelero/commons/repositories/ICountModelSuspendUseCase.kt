package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel

interface ICountModelSuspendUseCase<Model : IModel<*, *, *>> : ICountChildModelSuspendUseCase<Model, Unit> {

    suspend operator fun invoke(): Long

    override suspend fun invoke(input: Unit): Long = invoke()

}
