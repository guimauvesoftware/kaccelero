package dev.kaccelero.repositories

import dev.kaccelero.models.IModel

interface IAPIModelRemoteRepository<Model : IModel<Id, CreatePayload, UpdatePayload>, Id, CreatePayload : Any, UpdatePayload : Any> :
    IAPIChildModelRemoteRepository<Model, Id, CreatePayload, UpdatePayload, Unit>,
    IModelRemoteRepository<Model, Id, CreatePayload, UpdatePayload>
