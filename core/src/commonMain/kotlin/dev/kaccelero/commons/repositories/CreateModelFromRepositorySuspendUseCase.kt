package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository

open class CreateModelFromRepositorySuspendUseCase<Model : IModel<*, CreatePayload, *>, CreatePayload>(
    repository: IModelSuspendRepository<Model, *, CreatePayload, *>,
) : CreateChildModelFromRepositorySuspendUseCase<Model, CreatePayload, Unit>(repository),
    ICreateModelSuspendUseCase<Model, CreatePayload> {

    override suspend fun invoke(input: CreatePayload): Model? = invoke(input, Unit)

    override suspend fun invoke(input1: CreatePayload, input2: Unit): Model? =
        super<CreateChildModelFromRepositorySuspendUseCase>.invoke(input1, input2)

}
