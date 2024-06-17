package dev.kaccelero.routers

import dev.kaccelero.controllers.IUnitController
import dev.kaccelero.controllers.UnitController
import dev.kaccelero.models.UnitModel
import io.ktor.server.application.*
import io.ktor.util.reflect.*
import kotlin.reflect.KClass

open class TemplateUnitRouter(
    controller: IUnitController = UnitController,
    controllerClass: KClass<out IUnitController> = UnitController::class,
    respondTemplate: suspend ApplicationCall.(String, Map<String, Any?>) -> Unit,
    errorTemplate: String? = null,
    redirectUnauthorizedToUrl: String? = null,
    route: String? = null,
    prefix: String? = null,
) : TemplateModelRouter<UnitModel, Unit, Unit, Unit>(
    typeInfo<UnitModel>(),
    typeInfo<Unit>(),
    typeInfo<Unit>(),
    controller,
    controllerClass,
    respondTemplate,
    errorTemplate,
    redirectUnauthorizedToUrl,
    route = route ?: "",
    prefix = prefix
), IUnitRouter
