package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.usecases.IUseCase
import kotlin.js.JsExport

@JsExport
interface ICountChildModelUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId> : IUseCase<ParentId, Long>
