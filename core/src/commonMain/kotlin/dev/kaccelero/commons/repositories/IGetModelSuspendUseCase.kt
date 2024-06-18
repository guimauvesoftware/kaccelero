package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel

interface IGetModelSuspendUseCase<Model : IModel<Id, *, *>, Id> : IGetChildModelSuspendUseCase<Model, Id, Unit> {

    suspend operator fun invoke(input: Id): Model?

    override suspend fun invoke(input1: Id, input2: Unit): Model? = invoke(input1)

}
