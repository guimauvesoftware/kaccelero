package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.repositories.Pagination
import dev.kaccelero.usecases.ITripleSuspendUseCase

interface IListSliceChildModelWithContextSuspendUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId> :
    ITripleSuspendUseCase<Pagination, ParentId, IContext, List<Model>>
