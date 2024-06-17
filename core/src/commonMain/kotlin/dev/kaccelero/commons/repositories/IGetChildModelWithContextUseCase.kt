package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.usecases.ITripleUseCase
import kotlin.js.JsExport

@JsExport
interface IGetChildModelWithContextUseCase<Model : IChildModel<Id, *, *, ParentId>, Id, ParentId> :
    ITripleUseCase<Id, ParentId, IContext, Model?>
