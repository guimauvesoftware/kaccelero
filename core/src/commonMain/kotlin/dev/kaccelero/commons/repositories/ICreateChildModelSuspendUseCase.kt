package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.usecases.IPairSuspendUseCase

interface ICreateChildModelSuspendUseCase<Model : IChildModel<*, CreatePayload, *, ParentId>, CreatePayload, ParentId> :
    IPairSuspendUseCase<CreatePayload, ParentId, Model?>
