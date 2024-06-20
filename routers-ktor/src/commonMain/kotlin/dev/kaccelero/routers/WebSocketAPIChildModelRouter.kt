package dev.kaccelero.routers

import dev.kaccelero.annotations.WebSocketMapping
import dev.kaccelero.controllers.IChildModelController
import dev.kaccelero.models.IChildModel
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.util.reflect.*
import io.swagger.v3.oas.models.OpenAPI
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

open class WebSocketAPIChildModelRouter<Model : IChildModel<Id, CreatePayload, UpdatePayload, ParentId>, Id, CreatePayload : Any, UpdatePayload : Any, ParentModel : IChildModel<ParentId, *, *, *>, ParentId>(
    modelTypeInfo: TypeInfo,
    createPayloadTypeInfo: TypeInfo,
    updatePayloadTypeInfo: TypeInfo,
    controller: IChildModelController<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>,
    controllerClass: KClass<out IChildModelController<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>>,
    parentRouter: IChildModelRouter<ParentModel, *, *, *, *, *>?,
    route: String? = null,
    id: String? = null,
    prefix: String? = null,
) : APIChildModelRouter<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>(
    modelTypeInfo,
    createPayloadTypeInfo,
    updatePayloadTypeInfo,
    controller,
    controllerClass,
    parentRouter,
    route,
    id,
    prefix
) {

    override fun createControllerRoute(root: Route, controllerRoute: ControllerRoute, openAPI: OpenAPI?) {
        val websocketMapping = controllerRoute.annotations.firstNotNullOfOrNull { it as? WebSocketMapping }
            ?: return super.createControllerRoute(root, controllerRoute, openAPI)
        val path = getControllerRoutePath(controllerRoute)

        root.webSocket(fullRoute + path) {
            try {
                invokeControllerRoute(call, controllerRoute) { parameter ->
                    if (parameter.type == typeOf<DefaultWebSocketServerSession>()) return@invokeControllerRoute this
                    null
                }
            } catch (exception: Exception) {
                handleExceptionAPI(exception, call)
            }
        }
    }

}
