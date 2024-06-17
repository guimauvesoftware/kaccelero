package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.usecases.ITripleSuspendUseCase

interface ICreateChildModelWithContextSuspendUseCase<Model : IChildModel<*, CreatePayload, *, ParentId>, CreatePayload, ParentId> :
    ITripleSuspendUseCase<CreatePayload, ParentId, IContext, Model?>
