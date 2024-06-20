package dev.kaccelero.routers

import dev.kaccelero.controllers.IUnitController
import dev.kaccelero.controllers.UnitController
import dev.kaccelero.models.UnitModel
import io.ktor.util.reflect.*
import kotlin.reflect.KClass

open class WebSocketAPIUnitRouter(
    controller: IUnitController = UnitController,
    controllerClass: KClass<out IUnitController>,
    route: String? = null,
    prefix: String? = null,
) : WebSocketAPIModelRouter<UnitModel, Unit, Unit, Unit>(
    typeInfo<UnitModel>(),
    typeInfo<Unit>(),
    typeInfo<Unit>(),
    controller,
    controllerClass,
    route = route ?: "",
    prefix = prefix
), IUnitRouter
