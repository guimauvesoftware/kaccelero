package dev.kaccelero.routers

interface IRouter {

    fun createRoutes(root: IRoute, openAPI: IOpenAPI? = null)

}
