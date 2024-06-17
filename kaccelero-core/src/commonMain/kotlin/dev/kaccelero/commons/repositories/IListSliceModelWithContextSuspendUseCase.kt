package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.Pagination

interface IListSliceModelWithContextSuspendUseCase<Model : IModel<*, *, *>> :
    IListSliceChildModelWithContextSuspendUseCase<Model, Unit> {

    suspend operator fun invoke(input1: Pagination, input2: IContext): List<Model>

    override suspend fun invoke(input1: Pagination, input2: Unit, input3: IContext): List<Model> =
        invoke(input1, input3)

}
