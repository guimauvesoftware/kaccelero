package dev.kaccelero.models

import kotlin.js.JsExport

@JsExport
interface IChildModel<Id, CreatePayload, UpdatePayload, ParentId> {

    val id: Id
    val parentId: ParentId

}
