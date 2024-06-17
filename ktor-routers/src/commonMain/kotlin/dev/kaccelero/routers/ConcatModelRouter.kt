package dev.kaccelero.routers

import dev.kaccelero.models.IModel
import dev.kaccelero.models.UnitModel

open class ConcatModelRouter<Model : IModel<Id, CreatePayload, UpdatePayload>, Id, CreatePayload : Any, UpdatePayload : Any>(
    vararg routers: IModelRouter<Model, Id, CreatePayload, UpdatePayload>,
) : ConcatChildModelRouter<Model, Id, CreatePayload, UpdatePayload, UnitModel, Unit>(*routers),
    IModelRouter<Model, Id, CreatePayload, UpdatePayload>
