package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel

interface ICountModelWithContextSuspendUseCase<Model : IModel<*, *, *>> :
    ICountChildModelWithContextSuspendUseCase<Model, Unit> {

    suspend operator fun invoke(input: IContext): Long

    override suspend fun invoke(input1: Unit, input2: IContext): Long = invoke(input2)

}
