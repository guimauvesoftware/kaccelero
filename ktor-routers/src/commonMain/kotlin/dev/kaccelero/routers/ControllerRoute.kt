package dev.kaccelero.routers

import io.ktor.http.*
import kotlin.reflect.KParameter
import kotlin.reflect.KType

data class ControllerRoute(
    val type: RouteType,
    val path: String?,
    val method: HttpMethod?,
    val annotations: List<Annotation>,
    val parameters: List<KParameter>,
    val returnType: KType,
    val handler: suspend (args: Map<KParameter, Any?>) -> Any?,
)
