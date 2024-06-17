package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.repositories.IChildModelSuspendRepository

open class ListChildModelFromRepositorySuspendUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId>(
    private val repository: IChildModelSuspendRepository<Model, *, *, *, ParentId>,
) : IListChildModelSuspendUseCase<Model, ParentId> {

    override suspend fun invoke(input: ParentId): List<Model> = repository.list(input)

}
