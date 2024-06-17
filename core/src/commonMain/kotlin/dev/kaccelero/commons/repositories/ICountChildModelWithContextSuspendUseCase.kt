package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.usecases.IPairSuspendUseCase

interface ICountChildModelWithContextSuspendUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId> :
    IPairSuspendUseCase<ParentId, IContext, Long>
