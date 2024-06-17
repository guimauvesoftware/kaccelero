package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.repositories.IChildModelSuspendRepository

open class CountChildModelWithContextFromRepositorySuspendUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId>(
    private val repository: IChildModelSuspendRepository<Model, *, *, *, ParentId>,
) : ICountChildModelWithContextSuspendUseCase<Model, ParentId> {

    override suspend fun invoke(input1: ParentId, input2: IContext): Long = repository.count(input1, input2)

}
