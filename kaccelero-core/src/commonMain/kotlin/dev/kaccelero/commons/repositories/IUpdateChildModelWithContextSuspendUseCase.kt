package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.usecases.IQuadSuspendUseCase

interface IUpdateChildModelWithContextSuspendUseCase<Model : IChildModel<Id, *, UpdatePayload, ParentId>, Id, UpdatePayload, ParentId> :
    IQuadSuspendUseCase<Id, UpdatePayload, ParentId, IContext, Model?>
