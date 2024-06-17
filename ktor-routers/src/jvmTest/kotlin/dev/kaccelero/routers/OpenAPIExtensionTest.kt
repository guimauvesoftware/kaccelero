package dev.kaccelero.routers

import dev.kaccelero.mocks.TestModel
import dev.kaccelero.mocks.TestRecursiveModel
import io.swagger.v3.oas.models.OpenAPI
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

class OpenAPIExtensionTest {

    @Test
    fun returnsActualType() {
        assertEquals(typeOf<String>(), typeOf<String>().underlyingType)
    }

    @Test
    fun returnsTypeFromList() {
        assertEquals(typeOf<String>(), typeOf<List<String>>().underlyingType)
    }

    @Test
    fun returnsTypeFromArray() {
        assertEquals(typeOf<String>(), typeOf<Array<String>>().underlyingType)
    }

    @Test
    fun testOpenAPISchemaList() {
        val openAPI = OpenAPI()
        val actual = openAPI.schema(typeOf<List<String>>())
        assertEquals(String::class.qualifiedName, actual.items.type)
    }

    @Test
    fun testOpenAPISchemaMap() {
        val openAPI = OpenAPI()
        val actual = openAPI.schema(typeOf<Map<String, String>>())
        assertEquals("object", actual.type)
        assertEquals(null, actual.properties)
    }

    @Test
    fun testOpenAPISchemaTestModel() {
        val openAPI = OpenAPI()
        val actual = openAPI.schema(typeOf<TestModel>())
        assertEquals(TestModel::class.qualifiedName, actual.type)
        val schema = openAPI.components.schemas["dev.kaccelero.mocks.TestModel"]
        assertEquals("object", schema?.type)
        assertEquals(2, schema?.properties?.size)
        assertEquals(false, schema?.properties?.get("id")?.nullable)
        assertEquals("ID", schema?.properties?.get("id")?.description)
        assertEquals("123", schema?.properties?.get("id")?.example)
        assertEquals(false, schema?.properties?.get("string")?.nullable)
        assertEquals("String", schema?.properties?.get("string")?.description)
        assertEquals("abc", schema?.properties?.get("string")?.example)
        assertEquals(listOf("id", "string"), schema?.required)
    }

    @Test
    fun testOpenAPIRecursiveModel() {
        val openAPI = OpenAPI()
        val actual = openAPI.schema(typeOf<TestRecursiveModel>())
        assertEquals(TestRecursiveModel::class.qualifiedName, actual.type)
        val schema = openAPI.components.schemas["dev.kaccelero.mocks.TestRecursiveModel"]
        assertEquals("object", schema?.type)
        assertEquals(2, schema?.properties?.size)
        assertEquals(false, schema?.properties?.get("name")?.nullable)
        assertEquals("Name", schema?.properties?.get("name")?.description)
        assertEquals("abc", schema?.properties?.get("name")?.example)
        assertEquals(true, schema?.properties?.get("children")?.nullable)
        assertEquals("Children", schema?.properties?.get("children")?.description)
        assertEquals("[]", schema?.properties?.get("children")?.example)
        assertEquals(
            "#/components/schemas/dev.kaccelero.mocks.TestRecursiveModel",
            schema?.properties?.get("children")?.items?.`$ref`
        )
    }

}
