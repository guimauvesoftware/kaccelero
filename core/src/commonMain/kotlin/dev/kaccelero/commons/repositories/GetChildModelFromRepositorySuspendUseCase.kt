package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.repositories.IChildModelSuspendRepository

open class GetChildModelFromRepositorySuspendUseCase<Model : IChildModel<Id, *, *, ParentId>, Id, ParentId>(
    private val repository: IChildModelSuspendRepository<Model, Id, *, *, ParentId>,
) : IGetChildModelSuspendUseCase<Model, Id, ParentId> {

    override suspend fun invoke(input1: Id, input2: ParentId): Model? = repository.get(input1, input2)

}
