package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.usecases.IPairUseCase
import kotlin.js.JsExport

@JsExport
interface ICreateChildModelUseCase<Model : IChildModel<*, CreatePayload, *, ParentId>, CreatePayload, ParentId> :
    IPairUseCase<CreatePayload, ParentId, Model?>
