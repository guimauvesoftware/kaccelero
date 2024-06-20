package dev.kaccelero.routers

import dev.kaccelero.annotations.AdminTemplateMapping
import dev.kaccelero.annotations.TemplateMapping
import dev.kaccelero.controllers.IChildModelController
import dev.kaccelero.models.IChildModel
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import io.swagger.v3.oas.models.OpenAPI
import kotlin.reflect.KClass

open class AdminChildModelRouter<Model : IChildModel<Id, CreatePayload, UpdatePayload, ParentId>, Id, CreatePayload : Any, UpdatePayload : Any, ParentModel : IChildModel<ParentId, *, *, *>, ParentId>(
    modelTypeInfo: TypeInfo,
    createPayloadTypeInfo: TypeInfo,
    updatePayloadTypeInfo: TypeInfo,
    controller: IChildModelController<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>,
    controllerClass: KClass<out IChildModelController<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>>,
    parentRouter: IChildModelRouter<ParentModel, ParentId, *, *, *, *>?,
    respondTemplate: suspend ApplicationCall.(String, Map<String, Any?>) -> Unit,
    errorTemplate: String? = null,
    redirectUnauthorizedToUrl: String? = null,
    val listTemplate: String? = null,
    val getTemplate: String? = null,
    val createTemplate: String? = null,
    val updateTemplate: String? = null,
    val deleteTemplate: String? = null,
    route: String? = null,
    id: String? = null,
    prefix: String? = null,
) : TemplateChildModelRouter<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>(
    modelTypeInfo,
    createPayloadTypeInfo,
    updatePayloadTypeInfo,
    controller,
    controllerClass,
    parentRouter,
    respondTemplate,
    errorTemplate,
    redirectUnauthorizedToUrl,
    route,
    id,
    prefix ?: "/admin"
) {

    override fun createControllerRoute(root: Route, controllerRoute: ControllerRoute, openAPI: OpenAPI?) {
        val mapping = controllerRoute.annotations.firstNotNullOfOrNull { it as? AdminTemplateMapping } ?: return
        val effectiveTemplate = mapping.template.takeIf { it.isNotEmpty() } ?: when (controllerRoute.type) {
            RouteType.listModel -> listTemplate
            RouteType.getModel -> getTemplate
            RouteType.createModel -> createTemplate
            RouteType.updateModel -> updateTemplate
            RouteType.deleteModel -> deleteTemplate
            else -> null
        } ?: return

        super.createControllerRoute(
            root,
            controllerRoute.copy(
                annotations = controllerRoute.annotations.mapNotNull {
                    when (it) {
                        is AdminTemplateMapping -> TemplateMapping(effectiveTemplate)
                        is TemplateMapping -> null
                        else -> it
                    }
                }
            ),
            openAPI
        )
    }

}
