package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel

interface IUpdateModelWithContextSuspendUseCase<Model : IModel<Id, *, UpdatePayload>, Id, UpdatePayload> :
    IUpdateChildModelWithContextSuspendUseCase<Model, Id, UpdatePayload, Unit> {

    suspend operator fun invoke(input1: Id, input2: UpdatePayload, input3: IContext): Model?

    override suspend fun invoke(input1: Id, input2: UpdatePayload, input3: Unit, input4: IContext): Model? =
        invoke(input1, input2, input4)

}
