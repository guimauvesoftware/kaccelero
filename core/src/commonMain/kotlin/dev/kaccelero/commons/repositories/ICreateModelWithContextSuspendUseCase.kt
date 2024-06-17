package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel

interface ICreateModelWithContextSuspendUseCase<Model : IModel<*, CreatePayload, *>, CreatePayload> :
    ICreateChildModelWithContextSuspendUseCase<Model, CreatePayload, Unit> {

    suspend operator fun invoke(input1: CreatePayload, input2: IContext): Model?

    override suspend fun invoke(input1: CreatePayload, input2: Unit, input3: IContext): Model? = invoke(input1, input3)

}
