package dev.kaccelero.repositories

import dev.kaccelero.models.IContext
import dev.kaccelero.models.IModel

interface IModelSuspendRepository<Model : IModel<Id, CreatePayload, UpdatePayload>, Id, CreatePayload, UpdatePayload> :
    IChildModelSuspendRepository<Model, Id, CreatePayload, UpdatePayload, Unit> {

    suspend fun list(context: IContext? = null): List<Model> = throw UnsupportedOperationException()

    suspend fun list(pagination: Pagination, context: IContext? = null): List<Model> =
        throw UnsupportedOperationException()

    suspend fun count(context: IContext? = null): Long = throw UnsupportedOperationException()

    suspend fun get(id: Id, context: IContext? = null): Model? = throw UnsupportedOperationException()

    suspend fun create(payload: CreatePayload, context: IContext? = null): Model? =
        throw UnsupportedOperationException()

    suspend fun update(id: Id, payload: UpdatePayload, context: IContext? = null): Boolean =
        throw UnsupportedOperationException()

    suspend fun delete(id: Id, context: IContext? = null): Boolean = throw UnsupportedOperationException()

    override suspend fun list(parentId: Unit, context: IContext?): List<Model> = list(context)

    override suspend fun list(pagination: Pagination, parentId: Unit, context: IContext?): List<Model> =
        list(pagination, context)

    override suspend fun count(parentId: Unit, context: IContext?): Long = count(context)

    override suspend fun get(id: Id, parentId: Unit, context: IContext?): Model? = get(id, context)

    override suspend fun create(payload: CreatePayload, parentId: Unit, context: IContext?): Model? =
        create(payload, context)

    override suspend fun update(id: Id, payload: UpdatePayload, parentId: Unit, context: IContext?): Boolean =
        update(id, payload, context)

    override suspend fun delete(id: Id, parentId: Unit, context: IContext?): Boolean = delete(id, context)

}
