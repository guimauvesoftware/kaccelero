package dev.kaccelero.repositories

import dev.kaccelero.client.IAPIClient
import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel
import dev.kaccelero.models.RecursiveId
import dev.kaccelero.models.UnitModel
import io.ktor.util.reflect.*

open class APIModelRemoteRepository<Model : IModel<Id, CreatePayload, UpdatePayload>, Id, CreatePayload : Any, UpdatePayload : Any>(
    modelTypeInfo: TypeInfo,
    createPayloadTypeInfo: TypeInfo,
    updatePayloadTypeInfo: TypeInfo,
    listTypeInfo: TypeInfo,
    client: IAPIClient,
    route: String? = null,
    id: String? = null,
    prefix: String? = null,
) : APIChildModelRemoteRepository<Model, Id, CreatePayload, UpdatePayload, Unit>(
    modelTypeInfo,
    createPayloadTypeInfo,
    updatePayloadTypeInfo,
    listTypeInfo,
    client,
    null,
    route,
    id,
    prefix,
), IAPIModelRemoteRepository<Model, Id, CreatePayload, UpdatePayload> {

    override suspend fun list(parentId: RecursiveId<*, Unit, *>, context: IContext?): List<Model> =
        super<APIChildModelRemoteRepository>.list(parentId, context)

    override suspend fun list(context: IContext?): List<Model> =
        list(RecursiveId<UnitModel, Unit, Unit>(Unit), context)

    override suspend fun list(
        pagination: Pagination,
        parentId: RecursiveId<*, Unit, *>,
        context: IContext?,
    ): List<Model> = super<APIChildModelRemoteRepository>.list(pagination, parentId, context)

    override suspend fun list(pagination: Pagination, context: IContext?): List<Model> =
        list(pagination, RecursiveId<UnitModel, Unit, Unit>(Unit), context)

    override suspend fun get(id: Id, parentId: RecursiveId<*, Unit, *>, context: IContext?): Model? =
        super<APIChildModelRemoteRepository>.get(id, parentId, context)

    override suspend fun get(id: Id, context: IContext?): Model? =
        get(id, RecursiveId<UnitModel, Unit, Unit>(Unit), context)

    override suspend fun create(payload: CreatePayload, parentId: RecursiveId<*, Unit, *>, context: IContext?): Model? =
        super<APIChildModelRemoteRepository>.create(payload, parentId, context)

    override suspend fun create(payload: CreatePayload, context: IContext?): Model? =
        create(payload, RecursiveId<UnitModel, Unit, Unit>(Unit), context)

    override suspend fun delete(id: Id, parentId: RecursiveId<*, Unit, *>, context: IContext?): Boolean =
        super<APIChildModelRemoteRepository>.delete(id, parentId, context)

    override suspend fun delete(id: Id, context: IContext?): Boolean =
        delete(id, RecursiveId<UnitModel, Unit, Unit>(Unit), context)

    override suspend fun update(
        id: Id,
        payload: UpdatePayload,
        parentId: RecursiveId<*, Unit, *>,
        context: IContext?,
    ): Model? = super<APIChildModelRemoteRepository>.update(id, payload, parentId, context)

    override suspend fun update(id: Id, payload: UpdatePayload, context: IContext?): Model? =
        update(id, payload, RecursiveId<UnitModel, Unit, Unit>(Unit), context)

}
