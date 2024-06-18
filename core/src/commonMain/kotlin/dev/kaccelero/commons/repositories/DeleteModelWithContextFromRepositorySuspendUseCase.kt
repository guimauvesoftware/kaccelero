package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository

open class DeleteModelWithContextFromRepositorySuspendUseCase<Model : IModel<Id, *, *>, Id>(
    repository: IModelSuspendRepository<Model, Id, *, *>,
) : DeleteChildModelWithContextFromRepositorySuspendUseCase<Model, Id, Unit>(repository),
    IDeleteModelWithContextSuspendUseCase<Model, Id> {

    override suspend fun invoke(input1: Id, input2: IContext): Boolean = invoke(input1, Unit, input2)

    override suspend fun invoke(input1: Id, input2: Unit, input3: IContext): Boolean =
        super<DeleteChildModelWithContextFromRepositorySuspendUseCase>.invoke(input1, input2, input3)

}
