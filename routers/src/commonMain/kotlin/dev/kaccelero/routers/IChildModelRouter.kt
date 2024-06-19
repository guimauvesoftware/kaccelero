package dev.kaccelero.routers

import dev.kaccelero.controllers.IChildModelController
import dev.kaccelero.models.IChildModel
import kotlin.reflect.KClass
import kotlin.reflect.KType

interface IChildModelRouter<Model : IChildModel<Id, CreatePayload, UpdatePayload, ParentId>, Id, CreatePayload : Any, UpdatePayload : Any, ParentModel : IChildModel<ParentId, *, *, *>, ParentId> :
    IRouter {

    val modelType: KType?
    val createPayloadType: KType?
    val updatePayloadType: KType?

    val controller: IChildModelController<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>
    val controllerClass: KClass<out IChildModelController<Model, Id, CreatePayload, UpdatePayload, ParentModel, ParentId>>
    val parentRouter: IChildModelRouter<ParentModel, *, *, *, *, *>?

    val route: String
    val id: String
    val prefix: String

    val routeIncludingParent: String

    suspend fun get(call: ICall): Model

}
