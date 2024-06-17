package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository

open class GetModelFromRepositorySuspendUseCase<Model : IModel<Id, *, *>, Id>(
    repository: IModelSuspendRepository<Model, Id, *, *>,
) : GetChildModelFromRepositorySuspendUseCase<Model, Id, Unit>(repository), IGetModelSuspendUseCase<Model, Id> {

    override suspend fun invoke(input: Id): Model? = invoke(input, Unit)

    override suspend fun invoke(input1: Id, input2: Unit): Model? =
        super<GetChildModelFromRepositorySuspendUseCase>.invoke(input1, input2)

}
