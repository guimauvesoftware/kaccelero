package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository
import dev.kaccelero.repositories.Pagination

open class ListSliceModelFromRepositorySuspendUseCase<Model : IModel<*, *, *>>(
    repository: IModelSuspendRepository<Model, *, *, *>,
) : ListSliceChildModelFromRepositorySuspendUseCase<Model, Unit>(repository), IListSliceModelSuspendUseCase<Model> {

    override suspend fun invoke(input: Pagination): List<Model> = invoke(input, Unit)

    override suspend fun invoke(input1: Pagination, input2: Unit): List<Model> =
        super<ListSliceChildModelFromRepositorySuspendUseCase>.invoke(input1, input2)

}
