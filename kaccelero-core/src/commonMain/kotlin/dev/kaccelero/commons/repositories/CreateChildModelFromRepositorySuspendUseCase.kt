package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.repositories.IChildModelSuspendRepository

open class CreateChildModelFromRepositorySuspendUseCase<Model : IChildModel<*, CreatePayload, *, ParentId>, CreatePayload, ParentId>(
    private val repository: IChildModelSuspendRepository<Model, *, CreatePayload, *, ParentId>,
) : ICreateChildModelSuspendUseCase<Model, CreatePayload, ParentId> {

    override suspend fun invoke(input1: CreatePayload, input2: ParentId): Model? = repository.create(input1, input2)

}
