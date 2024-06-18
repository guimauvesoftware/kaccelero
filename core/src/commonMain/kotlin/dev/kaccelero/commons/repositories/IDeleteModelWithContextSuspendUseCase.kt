package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel

interface IDeleteModelWithContextSuspendUseCase<Model : IModel<Id, *, *>, Id> :
    IDeleteChildModelWithContextSuspendUseCase<Model, Id, Unit> {

    suspend operator fun invoke(input1: Id, input2: IContext): Boolean

    override suspend fun invoke(input1: Id, input2: Unit, input3: IContext): Boolean = invoke(input1, input3)

}
