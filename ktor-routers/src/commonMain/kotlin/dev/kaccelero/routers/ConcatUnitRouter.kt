package dev.kaccelero.routers

import dev.kaccelero.models.UnitModel

open class ConcatUnitRouter(
    vararg routers: IUnitRouter,
) : ConcatModelRouter<UnitModel, Unit, Unit, Unit>(*routers), IUnitRouter
