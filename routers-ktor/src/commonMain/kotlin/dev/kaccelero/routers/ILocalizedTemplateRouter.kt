package dev.kaccelero.routers

import dev.kaccelero.commons.localization.IGetLocaleForCallUseCase
import dev.kaccelero.plugins.I18n
import dev.kaccelero.plugins.LocalizedRouteInterceptor
import dev.kaccelero.plugins.LocalizedRouteSelector
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

interface ILocalizedTemplateRouter {

    companion object {

        fun wrapRespondTemplate(
            respondTemplate: suspend ApplicationCall.(String, Map<String, Any?>) -> Unit,
            getLocaleForCallUseCase: IGetLocaleForCallUseCase,
        ): suspend ApplicationCall.(String, Map<String, Any?>) -> Unit {
            return { template, model ->
                respondTemplate(template, model + mapOf("locale" to getLocaleForCallUseCase(this)))
            }
        }

    }

    fun localizeRoutes(root: IRoute, openAPI: IOpenAPI? = null) {
        if (root !is KtorRoute) return
        val localizedRoutes = root.route.createChild(LocalizedRouteSelector())
        localizedRoutes.install(LocalizedRouteInterceptor)

        createLocalizedRoutes(KtorRoute(localizedRoutes), openAPI)
        localizedRoutes.plugin(I18n).supportedLocales.forEach {
            localizedRoutes.route("/$it") {
                createLocalizedRoutes(KtorRoute(this))
            }
        }
    }

    fun isUnauthorizedRedirectPath(
        call: ApplicationCall,
        redirectUnauthorizedToUrl: String?,
        getLocaleForCallUseCase: IGetLocaleForCallUseCase,
    ): Boolean {
        return redirectUnauthorizedToUrl?.let {
            "/${getLocaleForCallUseCase(call)}$it"
        }?.startsWith(call.request.path()) == true
    }

    fun createLocalizedRoutes(root: IRoute, openAPI: IOpenAPI? = null)

}
