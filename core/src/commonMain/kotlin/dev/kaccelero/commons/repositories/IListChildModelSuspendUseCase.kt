package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.usecases.ISuspendUseCase

interface IListChildModelSuspendUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId> :
    ISuspendUseCase<ParentId, List<Model>>
