package dev.kaccelero.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.models.IContext
import dev.kaccelero.models.RecursiveId

interface IChildModelRemoteRepository<Model : IChildModel<Id, CreatePayload, UpdatePayload, ParentId>, Id, CreatePayload, UpdatePayload, ParentId> {

    suspend fun list(parentId: RecursiveId<*, ParentId, *>, context: IContext? = null): List<Model> =
        throw UnsupportedOperationException()

    suspend fun list(
        pagination: Pagination,
        parentId: RecursiveId<*, ParentId, *>,
        context: IContext? = null,
    ): List<Model> = throw UnsupportedOperationException()

    suspend fun count(parentId: RecursiveId<*, ParentId, *>, context: IContext? = null): Long =
        throw UnsupportedOperationException()

    suspend fun get(id: Id, parentId: RecursiveId<*, ParentId, *>, context: IContext? = null): Model? =
        throw UnsupportedOperationException()

    suspend fun create(
        payload: CreatePayload,
        parentId: RecursiveId<*, ParentId, *>,
        context: IContext? = null,
    ): Model? = throw UnsupportedOperationException()

    suspend fun update(
        id: Id,
        payload: UpdatePayload,
        parentId: RecursiveId<*, ParentId, *>,
        context: IContext? = null,
    ): Model? = throw UnsupportedOperationException()

    suspend fun delete(id: Id, parentId: RecursiveId<*, ParentId, *>, context: IContext? = null): Boolean =
        throw UnsupportedOperationException()

}
