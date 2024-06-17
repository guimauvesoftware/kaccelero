package dev.kaccelero.repositories

import dev.kaccelero.client.IAPIClient
import dev.kaccelero.models.UnitModel
import io.ktor.util.reflect.*

open class APIUnitRemoteRepository(
    client: IAPIClient,
    route: String? = null,
    prefix: String? = null,
) : APIModelRemoteRepository<UnitModel, Unit, Unit, Unit>(
    typeInfo<UnitModel>(),
    typeInfo<Unit>(),
    typeInfo<Unit>(),
    typeInfo<List<UnitModel>>(),
    client,
    route = route,
    prefix = prefix
)
