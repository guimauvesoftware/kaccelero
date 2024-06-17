package dev.kaccelero.commons.permissions

import dev.kaccelero.usecases.IPairUseCase
import kotlin.js.JsExport

@JsExport
interface ICheckPermissionUseCase : IPairUseCase<IPermittee, IPermission, Boolean>
