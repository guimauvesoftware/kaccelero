package dev.kaccelero.routers

data class RouteType(val value: String? = null) {

    companion object {

        val listModel = RouteType("listModel".lowercase())
        val getModel = RouteType("getModel".lowercase())
        val createModel = RouteType("createModel".lowercase())
        val updateModel = RouteType("updateModel".lowercase())
        val deleteModel = RouteType("deleteModel".lowercase())

    }

}
