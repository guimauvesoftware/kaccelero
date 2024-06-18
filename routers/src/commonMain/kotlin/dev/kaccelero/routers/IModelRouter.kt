package dev.kaccelero.routers

import dev.kaccelero.models.IModel
import dev.kaccelero.models.UnitModel

interface IModelRouter<Model : IModel<Id, CreatePayload, UpdatePayload>, Id, CreatePayload : Any, UpdatePayload : Any> :
    IChildModelRouter<Model, Id, CreatePayload, UpdatePayload, UnitModel, Unit>
