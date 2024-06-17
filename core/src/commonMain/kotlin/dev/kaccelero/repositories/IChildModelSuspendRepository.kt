package dev.kaccelero.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext

interface IChildModelSuspendRepository<Model : IChildModel<Id, CreatePayload, UpdatePayload, ParentId>, Id, CreatePayload, UpdatePayload, ParentId> {

    suspend fun list(parentId: ParentId, context: IContext? = null): List<Model> = throw UnsupportedOperationException()

    suspend fun list(pagination: Pagination, parentId: ParentId, context: IContext? = null): List<Model> =
        throw UnsupportedOperationException()

    suspend fun count(parentId: ParentId, context: IContext? = null): Long = throw UnsupportedOperationException()

    suspend fun get(id: Id, parentId: ParentId, context: IContext? = null): Model? =
        throw UnsupportedOperationException()

    suspend fun create(payload: CreatePayload, parentId: ParentId, context: IContext? = null): Model? =
        throw UnsupportedOperationException()

    suspend fun update(id: Id, payload: UpdatePayload, parentId: ParentId, context: IContext? = null): Boolean =
        throw UnsupportedOperationException()

    suspend fun delete(id: Id, parentId: ParentId, context: IContext? = null): Boolean =
        throw UnsupportedOperationException()

}
