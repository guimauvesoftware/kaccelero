package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel

interface IListModelWithContextSuspendUseCase<Model : IModel<*, *, *>> :
    IListChildModelWithContextSuspendUseCase<Model, Unit> {

    suspend operator fun invoke(input: IContext): List<Model>

    override suspend fun invoke(input1: Unit, input2: IContext): List<Model> = invoke(input2)

}
