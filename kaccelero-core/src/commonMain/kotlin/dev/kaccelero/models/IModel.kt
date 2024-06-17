package dev.kaccelero.models

import kotlin.js.JsExport

@JsExport
interface IModel<Id, CreatePayload, UpdatePayload> : IChildModel<Id, CreatePayload, UpdatePayload, Unit> {

    override val id: Id

    override val parentId: Unit
        get() = Unit

}
