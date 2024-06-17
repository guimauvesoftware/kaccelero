package dev.kaccelero.mocks

import dev.kaccelero.annotations.APIMapping
import dev.kaccelero.annotations.AdminTemplateMapping
import dev.kaccelero.annotations.Path
import dev.kaccelero.annotations.TemplateMapping
import dev.kaccelero.controllers.IUnitController

interface ITestUnitController : IUnitController {

    @APIMapping
    @TemplateMapping("hello.ftl")
    @Path("GET", "/hello")
    suspend fun hello(): String

    @APIMapping
    @AdminTemplateMapping("dashboard.ftl")
    @Path("GET", "/")
    suspend fun dashboard()

}
