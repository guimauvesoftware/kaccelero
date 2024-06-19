package dev.kaccelero.routers

import dev.kaccelero.controllers.IChildModelController
import dev.kaccelero.models.IChildModel
import kotlin.reflect.KClass
import kotlin.reflect.KType

open class ConcatChildModelRouter<Model : IChildModel<Id, CreatePayload, UpdatePayload, ParentId>, Id, CreatePayload : Any, UpdatePayload : Any, ParentModel : IChildModel<ParentId, *, *, *>, ParentId>(
    vararg val routers: IChildModelRouter<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>,
) : IChildModelRouter<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId> {

    override val modelType: KType?
        get() = routers.first().modelType

    override val createPayloadType: KType?
        get() = routers.first().createPayloadType

    override val updatePayloadType: KType?
        get() = routers.first().updatePayloadType

    override val controllerClass: KClass<out IChildModelController<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>>
        get() = routers.first().controllerClass

    override val controller: IChildModelController<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>
        get() = routers.first().controller

    override val parentRouter: IChildModelRouter<ParentModel, *, *, *, *, *>?
        get() = routers.first().parentRouter

    override val route: String
        get() = routers.first().route

    override val id: String
        get() = routers.first().id

    override val prefix: String
        get() = routers.first().prefix

    override val routeIncludingParent: String
        get() = routers.first().routeIncludingParent

    override suspend fun get(call: ICall): Model =
        routers.first().get(call)

    override fun createRoutes(root: IRoute, openAPI: IOpenAPI?) =
        routers.forEach { it.createRoutes(root, openAPI) }

    inline fun <reified T> routersOf(): List<T> =
        routers.filterIsInstance<T>()

    inline fun <reified T> routerOf(): T =
        routersOf<T>().first()

    inline fun <reified T> routerOfOrNull(): T? =
        routersOf<T>().firstOrNull()

}
