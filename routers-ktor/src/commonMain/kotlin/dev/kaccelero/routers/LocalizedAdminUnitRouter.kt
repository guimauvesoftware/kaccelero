package dev.kaccelero.routers

import dev.kaccelero.commons.localization.IGetLocaleForCallUseCase
import dev.kaccelero.controllers.IUnitController
import dev.kaccelero.controllers.UnitController
import io.ktor.server.application.*
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

    final override fun createRoutes(root: IRoute, openAPI: IOpenAPI?) =
        localizeRoutes(root, openAPI)

    override fun isUnauthorizedRedirectPath(call: ApplicationCall): Boolean =
        isUnauthorizedRedirectPath(call, redirectUnauthorizedToUrl, getLocaleForCallUseCase)

    override fun createLocalizedRoutes(root: IRoute, openAPI: IOpenAPI?) =
        super.createRoutes(root, openAPI)

}
