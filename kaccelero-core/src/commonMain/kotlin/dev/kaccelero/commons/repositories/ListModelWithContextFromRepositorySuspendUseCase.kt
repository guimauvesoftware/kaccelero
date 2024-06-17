package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel
import dev.kaccelero.repositories.IModelSuspendRepository

open class ListModelWithContextFromRepositorySuspendUseCase<Model : IModel<*, *, *>>(
    repository: IModelSuspendRepository<Model, *, *, *>,
) : ListChildModelWithContextFromRepositorySuspendUseCase<Model, Unit>(repository),
    IListModelWithContextSuspendUseCase<Model> {

    override suspend fun invoke(input: IContext): List<Model> = invoke(Unit, input)

    override suspend fun invoke(input1: Unit, input2: IContext): List<Model> =
        super<ListChildModelWithContextFromRepositorySuspendUseCase>.invoke(input1, input2)

}
