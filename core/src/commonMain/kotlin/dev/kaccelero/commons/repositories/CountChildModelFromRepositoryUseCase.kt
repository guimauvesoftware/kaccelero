package dev.kaccelero.commons.repositories

import dev.kaccelero.models.IChildModel
import dev.kaccelero.repositories.IChildModelRepository
import kotlin.js.JsExport

@JsExport
open class CountChildModelFromRepositoryUseCase<Model : IChildModel<*, *, *, ParentId>, ParentId>(
    private val repository: IChildModelRepository<Model, *, *, *, ParentId>,
) : ICountChildModelUseCase<Model, ParentId> {

    override fun invoke(input: ParentId): Long = repository.count(input)

}
