package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository

open class CountModelWithContextFromRepositorySuspendUseCase<Model : IModel<*, *, *>>(
    repository: IModelSuspendRepository<Model, *, *, *>,
) : CountChildModelWithContextFromRepositorySuspendUseCase<Model, Unit>(repository),
    ICountModelWithContextSuspendUseCase<Model> {

    override suspend fun invoke(input: IContext): Long = invoke(Unit, input)

    override suspend fun invoke(input1: Unit, input2: IContext): Long =
        super<CountChildModelWithContextFromRepositorySuspendUseCase>.invoke(input1, input2)

}
