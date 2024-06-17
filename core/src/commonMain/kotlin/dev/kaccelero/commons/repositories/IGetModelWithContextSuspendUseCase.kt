package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel

interface IGetModelWithContextSuspendUseCase<Model : IModel<Id, *, *>, Id> :
    IGetChildModelWithContextSuspendUseCase<Model, Id, Unit> {

    suspend operator fun invoke(input1: Id, input2: IContext): Model?

    override suspend fun invoke(input1: Id, input2: Unit, input3: IContext): Model? = invoke(input1, input3)

}
