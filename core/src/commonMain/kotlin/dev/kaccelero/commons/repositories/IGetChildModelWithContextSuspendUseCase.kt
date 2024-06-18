package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.usecases.ITripleSuspendUseCase

interface IGetChildModelWithContextSuspendUseCase<Model : IChildModel<Id, *, *, ParentId>, Id, ParentId> :
    ITripleSuspendUseCase<Id, ParentId, IContext, Model?>
