package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.repositories.IChildModelSuspendRepository

open class ListChildModelWithContextFromRepositorySuspendUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId>(
    private val repository: IChildModelSuspendRepository<Model, *, *, *, ParentId>,
) : IListChildModelWithContextSuspendUseCase<Model, ParentId> {

    override suspend fun invoke(input1: ParentId, input2: IContext): List<Model> = repository.list(input1, input2)

}
