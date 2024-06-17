package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository
import dev.kaccelero.repositories.Pagination

open class ListSliceModelWithContextFromRepositorySuspendUseCase<Model : IModel<*, *, *>>(
    repository: IModelSuspendRepository<Model, *, *, *>,
) : ListSliceChildModelWithContextFromRepositorySuspendUseCase<Model, Unit>(repository),
    IListSliceModelWithContextSuspendUseCase<Model> {

    override suspend fun invoke(input1: Pagination, input2: IContext): List<Model> = invoke(input1, Unit, input2)

    override suspend fun invoke(input1: Pagination, input2: Unit, input3: IContext): List<Model> =
        super<ListSliceChildModelWithContextFromRepositorySuspendUseCase>.invoke(input1, input2, input3)

}
