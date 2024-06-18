package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository

open class ListModelFromRepositorySuspendUseCase<Model : IModel<*, *, *>>(
    repository: IModelSuspendRepository<Model, *, *, *>,
) : ListChildModelFromRepositorySuspendUseCase<Model, Unit>(repository), IListModelSuspendUseCase<Model> {

    override suspend fun invoke(): List<Model> = invoke(Unit)

    override suspend fun invoke(input: Unit): List<Model> =
        super<ListChildModelFromRepositorySuspendUseCase>.invoke(input)

}
