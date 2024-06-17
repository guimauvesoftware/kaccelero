package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.Pagination

interface IListSliceModelSuspendUseCase<Model : IModel<*, *, *>> : IListSliceChildModelSuspendUseCase<Model, Unit> {

    suspend operator fun invoke(input: Pagination): List<Model>

    override suspend fun invoke(input1: Pagination, input2: Unit): List<Model> = invoke(input1)

}
