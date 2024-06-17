package dev.kaccelero.routers

import dev.kaccelero.annotations.*
import dev.kaccelero.controllers.IChildModelController
import dev.kaccelero.models.IChildModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.parameters.Parameter
import java.lang.reflect.InvocationTargetException
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.callSuspendBy
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.typeOf

@Suppress("UNCHECKED_CAST")
abstract class AbstractChildModelRouter<Model : IChildModel<Id, CreatePayload, UpdatePayload, ParentId>, Id, CreatePayload : Any, UpdatePayload : Any, ParentModel : IChildModel<ParentId, *, *, *>, ParentId>(
    final override val modelTypeInfo: TypeInfo,
    final override val createPayloadTypeInfo: TypeInfo,
    final override val updatePayloadTypeInfo: TypeInfo,
    final override val controller: IChildModelController<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>,
    final override val controllerClass: KClass<out IChildModelController<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>>,
    final override val parentRouter: IChildModelRouter<ParentModel, *, *, *, *, *>?,
    route: String? = null,
    id: String? = null,
    prefix: String? = null,
) : IChildModelRouter<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId> {

    // Parameters linked to routing

    final override val route = route ?: (modelTypeInfo.type.simpleName!!.lowercase() + "s")
    final override val id = id ?: (modelTypeInfo.type.simpleName!!.lowercase() + "Id")
    final override val prefix = prefix ?: ""

    final override val routeIncludingParent = ((parentRouter?.let {
        val parentRoute = it
            .routeIncludingParent
            .trim('/')
            .takeIf(String::isNotEmpty)
            ?.let { r -> "/$r" } ?: ""
        val parentId = it.id.takeIf(String::isNotEmpty)?.let { i -> "/{$i}" } ?: ""
        parentRoute + parentId
    } ?: "") + "/" + this.route.trim('/')).removeSuffix("/")

    val fullRoute = this.prefix + routeIncludingParent

    // Keys for model

    val modelKeys = ModelAnnotations.modelKeys(modelTypeInfo.type as KClass<Model>)
    val createPayloadKeys = ModelAnnotations.createPayloadKeys(
        modelTypeInfo.type as KClass<Model>,
        createPayloadTypeInfo.type as KClass<CreatePayload>
    )
    val updatePayloadKeys = ModelAnnotations.updatePayloadKeys(
        modelTypeInfo.type as KClass<Model>,
        updatePayloadTypeInfo.type as KClass<UpdatePayload>
    )

    // Route calculation

    val controllerRoutes = controllerClass.memberFunctions.mapNotNull {
        val typeAnnotation = it.annotations.mapNotNull { annotation ->
            if (annotation.annotationClass.simpleName?.endsWith("Path") == true) Triple(
                RouteType(annotation.annotationClass.simpleName!!.removeSuffix("Path").lowercase()),
                annotation.annotationClass.members.firstOrNull { parameter -> parameter.name == "path" }
                    ?.call(annotation) as? String,
                annotation.annotationClass.members.firstOrNull { parameter -> parameter.name == "method" }
                    ?.call(annotation) as? String
            ) else null
        }.singleOrNull() ?: return@mapNotNull null
        ControllerRoute(
            typeAnnotation.first,
            typeAnnotation.second?.takeIf { path -> path.isNotEmpty() },
            typeAnnotation.third?.let { method -> HttpMethod.parse(method.uppercase()) },
            it.annotations,
            it.parameters,
            it.returnType,
            it::callSuspendBy
        )
    }

    override fun createRoutes(root: Route, openAPI: OpenAPI?) {
        controllerRoutes.forEach { createControllerRoute(root, it, openAPI) }
    }

    abstract fun createControllerRoute(root: Route, controllerRoute: ControllerRoute, openAPI: OpenAPI?)

    open suspend fun invokeControllerRoute(
        call: ApplicationCall,
        controllerRoute: ControllerRoute,
        mapParameter: (KParameter) -> Any? = { null },
    ): Any? {
        try {
            return controllerRoute.handler(controllerRoute.parameters.associateWith { parameter ->
                if (parameter.kind == KParameter.Kind.INSTANCE) return@associateWith controller
                if (parameter.type == typeOf<ApplicationCall>()) return@associateWith call
                val annotations = parameter.annotations
                annotations.firstNotNullOfOrNull { it as? dev.kaccelero.annotations.Id }?.let {
                    return@associateWith ModelAnnotations.constructIdFromString(
                        modelTypeInfo.type as KClass<Model>,
                        call.parameters[id]!!
                    )
                }
                annotations.firstNotNullOfOrNull { it as? PathParameter }
                    ?.let {
                        return@associateWith ModelAnnotations.constructPrimitiveFromString(
                            parameter.type,
                            parameter.name?.let { call.parameters[it] }
                        )
                    }
                annotations.firstNotNullOfOrNull { it as? QueryParameter }
                    ?.let {
                        return@associateWith ModelAnnotations.constructPrimitiveFromString(
                            parameter.type,
                            parameter.name?.let { call.request.queryParameters[it] }
                        )
                    }
                annotations.firstNotNullOfOrNull { it as? Payload }?.let {
                    val type = parameter.type.classifier as KClass<Any>
                    if (type == Unit::class) return@associateWith Unit
                    val payload = decodePayload(call, type)
                    ModelAnnotations.validatePayload(payload, type)
                    return@associateWith payload
                }
                annotations.firstNotNullOfOrNull { it as? dev.kaccelero.annotations.ParentModel }?.let {
                    var target: IChildModelRouter<*, *, *, *, *, *> = this
                    do {
                        target = target.parentRouter
                            ?: throw IllegalArgumentException("Illegal parent model: ${parameter.type}")
                    } while (target.modelTypeInfo.kotlinType != parameter.type)
                    return@associateWith target.get(call)
                }
                mapParameter(parameter) ?: throw IllegalArgumentException("Unknown parameter: ${parameter.name}")
            })
        } catch (e: InvocationTargetException) {
            throw e.targetException
        }
    }

    // Decode payloads

    abstract suspend fun <Payload : Any> decodePayload(call: ApplicationCall, type: KClass<Payload>): Payload

    // Default operations

    override suspend fun get(call: ApplicationCall): Model {
        return controllerRoutes.singleOrNull { it.type == RouteType.getModel }?.let {
            invokeControllerRoute(call, it)
        } as Model
    }

    override fun getOpenAPIParameters(self: Boolean): List<Parameter> {
        return parentRouter?.getOpenAPIParameters().orEmpty() + if (self) listOf(
            Parameter()
                .name(id)
                .schema(Schema<Id>().type(modelTypeInfo.type.memberProperties.first { it.name == "id" }.returnType.toString()))
                .`in`("path")
                .description("Id of the ${modelTypeInfo.type.simpleName}")
                .required(true)
        ) else emptyList()
    }

}
