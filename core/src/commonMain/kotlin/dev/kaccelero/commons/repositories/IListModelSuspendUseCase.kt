package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel

interface IListModelSuspendUseCase<Model : IModel<*, *, *>> : IListChildModelSuspendUseCase<Model, Unit> {

    suspend operator fun invoke(): List<Model>

    override suspend fun invoke(input: Unit): List<Model> = invoke()

}
