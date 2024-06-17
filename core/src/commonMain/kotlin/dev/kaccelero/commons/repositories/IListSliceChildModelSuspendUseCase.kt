package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.repositories.Pagination
import dev.kaccelero.usecases.IPairSuspendUseCase

interface IListSliceChildModelSuspendUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId> :
    IPairSuspendUseCase<Pagination, ParentId, List<Model>>

