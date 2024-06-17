package dev.kaccelero.models

data class RecursiveId<Model : IChildModel<Id, *, *, ParentId>, Id, ParentId>(
    val id: Id,
    val parentId: RecursiveId<*, ParentId, *>? = null,
)
