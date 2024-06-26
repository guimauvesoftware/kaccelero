package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.usecases.IPairUseCase
import kotlin.js.JsExport

@JsExport
interface IListChildModelWithContextUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId> :
    IPairUseCase<ParentId, IContext, List<Model>>
