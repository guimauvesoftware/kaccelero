package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.repositories.IChildModelSuspendRepository

open class DeleteChildModelFromRepositorySuspendUseCase<Model : IChildModel<Id, *, *, ParentId>, Id, ParentId>(
    private val repository: IChildModelSuspendRepository<Model, Id, *, *, ParentId>,
) : IDeleteChildModelSuspendUseCase<Model, Id, ParentId> {

    override suspend fun invoke(input1: Id, input2: ParentId): Boolean = repository.delete(input1, input2)

}
