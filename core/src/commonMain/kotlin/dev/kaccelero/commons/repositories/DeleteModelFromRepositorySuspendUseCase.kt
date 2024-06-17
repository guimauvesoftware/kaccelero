package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository

open class DeleteModelFromRepositorySuspendUseCase<Model : IModel<Id, *, *>, Id>(
    repository: IModelSuspendRepository<Model, Id, *, *>,
) : DeleteChildModelFromRepositorySuspendUseCase<Model, Id, Unit>(repository), IDeleteModelSuspendUseCase<Model, Id> {

    override suspend fun invoke(input: Id): Boolean = invoke(input, Unit)

    override suspend fun invoke(input1: Id, input2: Unit): Boolean =
        super<DeleteChildModelFromRepositorySuspendUseCase>.invoke(input1, input2)

}
