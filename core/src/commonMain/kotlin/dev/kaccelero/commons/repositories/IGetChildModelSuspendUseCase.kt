package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.usecases.IPairSuspendUseCase

interface IGetChildModelSuspendUseCase<Model : IChildModel<Id, *, *, ParentId>, Id, ParentId> :
    IPairSuspendUseCase<Id, ParentId, Model?>
