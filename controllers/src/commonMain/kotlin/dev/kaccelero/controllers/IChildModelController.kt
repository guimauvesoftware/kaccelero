package dev.kaccelero.controllers

import dev.kaccelero.models.IChildModel

interface IChildModelController<Model : IChildModel<Id, CreatePayload, UpdatePayload, ParentId>, Id, CreatePayload, UpdatePayload, ParentModel : IChildModel<ParentId, *, *, *>, ParentId> :
    IController
