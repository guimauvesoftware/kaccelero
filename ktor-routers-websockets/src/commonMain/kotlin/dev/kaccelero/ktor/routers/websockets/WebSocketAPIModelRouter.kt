package dev.kaccelero.ktor.routers.websockets

import dev.kaccelero.controllers.IModelController
import dev.kaccelero.models.IModel
import dev.kaccelero.models.UnitModel
import dev.kaccelero.routers.IModelRouter
import io.ktor.util.reflect.*
import kotlin.reflect.KClass

open class WebSocketAPIModelRouter<Model : IModel<Id, CreatePayload, UpdatePayload>, Id, CreatePayload : Any, UpdatePayload : Any>(
    modelTypeInfo: TypeInfo,
    createPayloadTypeInfo: TypeInfo,
    updatePayloadTypeInfo: TypeInfo,
    controller: IModelController<Model, Id, CreatePayload, UpdatePayload>,
    controllerClass: KClass<out IModelController<Model, Id, CreatePayload, UpdatePayload>>,
    route: String? = null,
    id: String? = null,
    prefix: String? = null,
) : WebSocketAPIChildModelRouter<Model, Id, CreatePayload, UpdatePayload, UnitModel, Unit>(
    modelTypeInfo,
    createPayloadTypeInfo,
    updatePayloadTypeInfo,
    controller,
    controllerClass,
    null,
    route,
    id,
    prefix
), IModelRouter<Model, Id, CreatePayload, UpdatePayload>
