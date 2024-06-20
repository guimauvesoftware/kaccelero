package dev.kaccelero.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*

val LocalizedRouteInterceptor: RouteScopedPlugin<Unit> = createRouteScopedPlugin(
    "LocalizedRouteInterceptor",
) {

    val i18n = application.i18n

    on(LocalizedRouteHook) {
        val call = it.call
        val language = i18n.getLocaleForCallUseCase(call).language

        val uri = call.request.origin.uri.trimStart('/').trimEnd('/').split('/')
        if (!uri.first().matches(i18n.supportedPathPrefixes)) {
            val toRedirect = mutableListOf<String>(
                language,
                *uri.toTypedArray()
            ).joinToString("/", prefix = "/").trimEnd('/')
            call.respondRedirect(toRedirect)
            it.finish()
        }
    }

}
