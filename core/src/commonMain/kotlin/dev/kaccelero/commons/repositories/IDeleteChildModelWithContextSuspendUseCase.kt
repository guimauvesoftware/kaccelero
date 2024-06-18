package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.usecases.ITripleSuspendUseCase

interface IDeleteChildModelWithContextSuspendUseCase<Model : IChildModel<Id, *, *, ParentId>, Id, ParentId> :
    ITripleSuspendUseCase<Id, ParentId, IContext, Boolean>
