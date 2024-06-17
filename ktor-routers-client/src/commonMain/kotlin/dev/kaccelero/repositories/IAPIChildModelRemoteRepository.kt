package dev.kaccelero.repositories

import dev.kaccelero.models.IChildModel
import io.ktor.util.reflect.*

interface IAPIChildModelRemoteRepository<Model : IChildModel<Id, CreatePayload, UpdatePayload, ParentId>, Id, CreatePayload : Any, UpdatePayload : Any, ParentId> :
    IChildModelRemoteRepository<Model, Id, CreatePayload, UpdatePayload, ParentId> {

    val modelTypeInfo: TypeInfo
    val createPayloadTypeInfo: TypeInfo
    val updatePayloadTypeInfo: TypeInfo
    val listTypeInfo: TypeInfo

    val route: String
    val id: String
    val prefix: String

    fun constructRouteIncludingParent(parentId: Any?): String

}
