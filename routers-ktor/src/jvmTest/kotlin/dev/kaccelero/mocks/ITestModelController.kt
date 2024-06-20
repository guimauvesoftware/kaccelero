package dev.kaccelero.mocks

import dev.kaccelero.annotations.*
import dev.kaccelero.controllers.IModelController
import io.ktor.server.application.*

interface ITestModelController : IModelController<TestModel, Long, TestCreatePayload, TestUpdatePayload> {

    @APIMapping("basic", "Basic test")
    @TemplateMapping(template = "basic")
    @AdminTemplateMapping(template = "adminBasic")
    @Path("GET", "/basic")
    suspend fun basic(call: ApplicationCall): String

    @APIMapping
    @TemplateMapping(template = "basic")
    @AdminTemplateMapping(template = "adminBasic")
    @Path("GET", "/basic/map")
    suspend fun basicMap(call: ApplicationCall): Map<String, String>

    @APIMapping
    @TemplateMapping(template = "basic")
    @AdminTemplateMapping(template = "adminBasic")
    @Path("GET", "/basic/model")
    suspend fun basicModel(call: ApplicationCall): TestUser

    @APIMapping
    @TemplateMapping(template = "list")
    @AdminTemplateMapping(template = "adminList")
    @ListModelPath
    suspend fun list(call: ApplicationCall): List<TestModel>

    @APIMapping
    @TemplateMapping(template = "create")
    @AdminTemplateMapping(template = "adminCreate")
    @CreateModelPath
    suspend fun create(call: ApplicationCall, @Payload payload: TestCreatePayload): TestModel

    @APIMapping
    @TemplateMapping(template = "get")
    @AdminTemplateMapping(template = "adminGet")
    @GetModelPath
    @DocumentedError(404, "testmodels_not_found", "Test model not found")
    suspend fun get(call: ApplicationCall, @Id id: Long): TestModel

    @APIMapping
    @TemplateMapping(template = "update")
    @AdminTemplateMapping(template = "adminUpdate")
    @UpdateModelPath
    suspend fun update(call: ApplicationCall, @Id id: Long, @Payload payload: TestUpdatePayload): TestModel

    @APIMapping
    @TemplateMapping(template = "delete")
    @AdminTemplateMapping(template = "adminDelete")
    @DeleteModelPath
    @DocumentedType(TestModel::class)
    suspend fun delete(call: ApplicationCall, @Id id: Long)

    @APIMapping
    @Path("GET", "/recursive")
    suspend fun recursive(call: ApplicationCall): TestRecursiveModel

}
