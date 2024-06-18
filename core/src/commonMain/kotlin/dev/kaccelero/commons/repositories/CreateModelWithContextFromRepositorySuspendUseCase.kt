package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository

open class CreateModelWithContextFromRepositorySuspendUseCase<Model : IModel<*, CreatePayload, *>, CreatePayload>(
    repository: IModelSuspendRepository<Model, *, CreatePayload, *>,
) : CreateChildModelWithContextFromRepositorySuspendUseCase<Model, CreatePayload, Unit>(repository),
    ICreateModelWithContextSuspendUseCase<Model, CreatePayload> {

    override suspend fun invoke(input1: CreatePayload, input2: IContext): Model? = invoke(input1, Unit, input2)

    override suspend fun invoke(input1: CreatePayload, input2: Unit, input3: IContext): Model? =
        super<CreateChildModelWithContextFromRepositorySuspendUseCase>.invoke(input1, input2, input3)

}
