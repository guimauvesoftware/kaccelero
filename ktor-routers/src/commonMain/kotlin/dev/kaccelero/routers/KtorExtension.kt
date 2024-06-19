package dev.kaccelero.routers

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.swagger.v3.oas.models.OpenAPI

fun IRouter.createRoutes(root: Route, openAPI: OpenAPI? = null) =
    createRoutes(KtorRoute(root), openAPI?.let(::SwaggerOpenAPI))

suspend fun IChildModelRouter<*, *, *, *, *, *>.get(call: ApplicationCall) =
    get(KtorCall(call))
