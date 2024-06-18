package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository

open class CountModelFromRepositorySuspendUseCase<Model : IModel<*, *, *>>(
    repository: IModelSuspendRepository<Model, *, *, *>,
) : CountChildModelFromRepositorySuspendUseCase<Model, Unit>(repository), ICountModelSuspendUseCase<Model> {

    override suspend fun invoke(): Long = invoke(Unit)

    override suspend fun invoke(input: Unit): Long =
        super<CountChildModelFromRepositorySuspendUseCase>.invoke(input)

}
