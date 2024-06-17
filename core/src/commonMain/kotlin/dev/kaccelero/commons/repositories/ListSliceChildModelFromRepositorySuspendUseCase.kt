package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.repositories.IChildModelSuspendRepository
import dev.kaccelero.repositories.Pagination

open class ListSliceChildModelFromRepositorySuspendUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId>(
    private val repository: IChildModelSuspendRepository<Model, *, *, *, ParentId>,
) : IListSliceChildModelSuspendUseCase<Model, ParentId> {

    override suspend fun invoke(input1: Pagination, input2: ParentId): List<Model> = repository.list(input1, input2)

}
