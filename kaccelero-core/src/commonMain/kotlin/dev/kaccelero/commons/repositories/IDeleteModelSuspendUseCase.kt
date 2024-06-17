package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel

interface IDeleteModelSuspendUseCase<Model : IModel<Id, *, *>, Id> : IDeleteChildModelSuspendUseCase<Model, Id, Unit> {

    suspend operator fun invoke(input: Id): Boolean

    override suspend fun invoke(input1: Id, input2: Unit): Boolean = invoke(input1)

}
