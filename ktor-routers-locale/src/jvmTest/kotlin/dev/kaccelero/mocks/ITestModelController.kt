package dev.kaccelero.mocks

import dev.kaccelero.annotations.APIMapping
import dev.kaccelero.annotations.AdminTemplateMapping
import dev.kaccelero.annotations.ListModelPath
import dev.kaccelero.annotations.TemplateMapping
import dev.kaccelero.controllers.IModelController
import io.ktor.server.application.*

interface ITestModelController : IModelController<TestModel, Long, TestCreatePayload, TestUpdatePayload> {

    @APIMapping
    @TemplateMapping(template = "list")
    @AdminTemplateMapping(template = "adminList")
    @ListModelPath
    suspend fun list(call: ApplicationCall): List<TestModel>

}
