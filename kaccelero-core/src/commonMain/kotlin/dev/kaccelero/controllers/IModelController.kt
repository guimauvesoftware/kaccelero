package dev.kaccelero.controllers

import dev.kaccelero.models.IModel
import dev.kaccelero.models.UnitModel

interface IModelController<Model : IModel<Id, CreatePayload, UpdatePayload>, Id, CreatePayload, UpdatePayload> :
    IChildModelController<Model, Id, CreatePayload, UpdatePayload, UnitModel, Unit>
