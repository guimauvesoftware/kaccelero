package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.repositories.IChildModelRepository
import kotlin.js.JsExport

@JsExport
open class ListChildModelFromRepositoryUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId>(
    private val repository: IChildModelRepository<Model, *, *, *, ParentId>,
) : IListChildModelUseCase<Model, ParentId> {

    override fun invoke(input: ParentId): List<Model> = repository.list(input)

}
