package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.usecases.ITripleSuspendUseCase

interface IUpdateChildModelSuspendUseCase<Model : IChildModel<Id, *, UpdatePayload, ParentId>, Id, UpdatePayload, ParentId> :
    ITripleSuspendUseCase<Id, UpdatePayload, ParentId, Model?>
