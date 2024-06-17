package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.repositories.IChildModelSuspendRepository

open class GetChildModelWithContextFromRepositorySuspendUseCase<Model : IChildModel<Id, *, *, ParentId>, Id, ParentId>(
    private val repository: IChildModelSuspendRepository<Model, Id, *, *, ParentId>,
) : IGetChildModelWithContextSuspendUseCase<Model, Id, ParentId> {

    override suspend fun invoke(input1: Id, input2: ParentId, input3: IContext): Model? =
        repository.get(input1, input2, input3)

}
