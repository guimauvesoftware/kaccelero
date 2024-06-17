package dev.kaccelero.ktor.routers.admin

import dev.kaccelero.commons.localization.IGetLocaleForCallUseCase
import dev.kaccelero.controllers.IUnitController
import dev.kaccelero.controllers.UnitController
import dev.kaccelero.routers.AdminUnitRouter
import dev.kaccelero.routers.ILocalizedTemplateRouter
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.swagger.v3.oas.models.OpenAPI
import kotlin.reflect.KClass

open class LocalizedAdminUnitRouter(
    controller: IUnitController = UnitController,
    controllerClass: KClass<out IUnitController> = UnitController::class,
    respondTemplate: suspend ApplicationCall.(String, Map<String, Any?>) -> Unit,
    val getLocaleForCallUseCase: IGetLocaleForCallUseCase,
    errorTemplate: String? = null,
    redirectUnauthorizedToUrl: String? = null,
    route: String? = null,
    prefix: String? = null,
) : AdminUnitRouter(
    controller,
    controllerClass,
    ILocalizedTemplateRouter.wrapRespondTemplate(respondTemplate, getLocaleForCallUseCase),
    errorTemplate,
    redirectUnauthorizedToUrl,
    route,
    prefix
), ILocalizedTemplateRouter {

    final override fun createRoutes(root: Route, openAPI: OpenAPI?) =
        localizeRoutes(root, openAPI)

    override fun isUnauthorizedRedirectPath(call: ApplicationCall): Boolean =
        isUnauthorizedRedirectPath(call, redirectUnauthorizedToUrl, getLocaleForCallUseCase)

    override fun createLocalizedRoutes(root: Route, openAPI: OpenAPI?) =
        super.createRoutes(root, openAPI)

}
