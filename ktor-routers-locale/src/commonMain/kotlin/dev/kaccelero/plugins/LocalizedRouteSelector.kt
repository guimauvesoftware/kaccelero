package dev.kaccelero.plugins

import io.ktor.server.routing.*

class LocalizedRouteSelector : RouteSelector() {

    override fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation {
        return RouteSelectorEvaluation.Transparent
    }

}
