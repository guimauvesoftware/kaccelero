package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.repositories.IChildModelSuspendRepository
import dev.kaccelero.repositories.Pagination

open class ListSliceChildModelWithContextFromRepositorySuspendUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId>(
    private val repository: IChildModelSuspendRepository<Model, *, *, *, ParentId>,
) : IListSliceChildModelWithContextSuspendUseCase<Model, ParentId> {

    override suspend fun invoke(input1: Pagination, input2: ParentId, input3: IContext): List<Model> =
        repository.list(input1, input2, input3)

}
