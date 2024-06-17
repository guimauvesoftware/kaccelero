package dev.kaccelero.ktor.routers.admin

import dev.kaccelero.commons.localization.IGetLocaleForCallUseCase
import dev.kaccelero.controllers.IModelController
import dev.kaccelero.models.IModel
import dev.kaccelero.routers.AdminModelRouter
import dev.kaccelero.routers.ILocalizedTemplateRouter
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import io.swagger.v3.oas.models.OpenAPI
import kotlin.reflect.KClass

open class LocalizedAdminModelRouter<Model : IModel<Id, CreatePayload, UpdatePayload>, Id, CreatePayload : Any, UpdatePayload : Any>(
    modelTypeInfo: TypeInfo,
    createPayloadTypeInfo: TypeInfo,
    updatePayloadTypeInfo: TypeInfo,
    controller: IModelController<Model, Id, CreatePayload, UpdatePayload>,
    controllerClass: KClass<out IModelController<Model, Id, CreatePayload, UpdatePayload>>,
    respondTemplate: suspend ApplicationCall.(String, Map<String, Any?>) -> Unit,
    val getLocaleForCallUseCase: IGetLocaleForCallUseCase,
    errorTemplate: String? = null,
    redirectUnauthorizedToUrl: String? = null,
    listTemplate: String? = null,
    getTemplate: String? = null,
    createTemplate: String? = null,
    updateTemplate: String? = null,
    deleteTemplate: String? = null,
    route: String? = null,
    id: String? = null,
    prefix: String? = null,
) : AdminModelRouter<Model, Id, CreatePayload, UpdatePayload>(
    modelTypeInfo,
    createPayloadTypeInfo,
    updatePayloadTypeInfo,
    controller,
    controllerClass,
    ILocalizedTemplateRouter.wrapRespondTemplate(respondTemplate, getLocaleForCallUseCase),
    errorTemplate,
    redirectUnauthorizedToUrl,
    listTemplate,
    getTemplate,
    createTemplate,
    updateTemplate,
    deleteTemplate,
    route,
    id,
    prefix,
), ILocalizedTemplateRouter {

    final override fun createRoutes(root: Route, openAPI: OpenAPI?) =
        localizeRoutes(root, openAPI)

    override fun isUnauthorizedRedirectPath(call: ApplicationCall): Boolean =
        isUnauthorizedRedirectPath(call, redirectUnauthorizedToUrl, getLocaleForCallUseCase)

    override fun createLocalizedRoutes(root: Route, openAPI: OpenAPI?) =
        super.createRoutes(root, openAPI)

}
