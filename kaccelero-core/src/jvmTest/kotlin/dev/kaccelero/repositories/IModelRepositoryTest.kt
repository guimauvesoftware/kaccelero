package dev.kaccelero.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import dev.kaccelero.models.IContext
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class IModelRepositoryTest {

    @Test
    fun testList() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun list(context: IContext?): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(
            listOf(ModelTest(1, "test")),
            repository.list(Unit)
        )
    }

    @Test
    fun testListNullContext() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun list(context: IContext?): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(
            listOf(ModelTest(1, "test")),
            repository.list()
        )
    }

    @Test
    fun testListUnsupported() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.list(Unit)
        }
    }

    @Test
    fun testListLimitOffset() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun list(pagination: Pagination, context: IContext?): List<ModelTest> =
                listOf(ModelTest(1, "test"))
        }
        assertEquals(
            listOf(ModelTest(1, "test")),
            repository.list(Pagination(1, 0), Unit)
        )
    }

    @Test
    fun testListLimitOffsetNullContext() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun list(pagination: Pagination, context: IContext?): List<ModelTest> =
                listOf(ModelTest(1, "test"))
        }
        assertEquals(
            listOf(ModelTest(1, "test")),
            repository.list(Pagination(1, 0))
        )
    }

    @Test
    fun testListLimitOffsetUnsupported() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.list(Pagination(1, 0), Unit)
        }
    }

    @Test
    fun testCount() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun count(context: IContext?): Long = 1
        }
        assertEquals(
            1,
            repository.count(Unit)
        )
    }

    @Test
    fun testCountNullContext() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun count(context: IContext?): Long = 1
        }
        assertEquals(
            1,
            repository.count()
        )
    }

    @Test
    fun testCountUnsupported() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.count(Unit)
        }
    }

    @Test
    fun testGet() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun get(id: Long, context: IContext?): ModelTest = ModelTest(1, "test")
        }
        assertEquals(
            ModelTest(1, "test"),
            repository.get(1, Unit)
        )
    }

    @Test
    fun testGetNullContext() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun get(id: Long, context: IContext?): ModelTest = ModelTest(1, "test")
        }
        assertEquals(
            ModelTest(1, "test"),
            repository.get(1)
        )
    }

    @Test
    fun testGetUnsupported() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.get(1, Unit)
        }
    }

    @Test
    fun testCreate() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun create(payload: CreatePayloadTest, context: IContext?): ModelTest = ModelTest(1, payload.value)
        }
        assertEquals(
            ModelTest(1, "test"),
            repository.create(CreatePayloadTest("test"), Unit)
        )
    }

    @Test
    fun testCreateNullContext() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun create(payload: CreatePayloadTest, context: IContext?): ModelTest = ModelTest(1, payload.value)
        }
        assertEquals(
            ModelTest(1, "test"),
            repository.create(CreatePayloadTest("test"))
        )
    }

    @Test
    fun testCreateUnsupported() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.create(CreatePayloadTest("test"), Unit)
        }
    }

    @Test
    fun testUpdate() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun update(id: Long, payload: UpdatePayloadTest, context: IContext?): Boolean = true
        }
        assertEquals(
            true,
            repository.update(1, UpdatePayloadTest("test"), Unit)
        )
    }

    @Test
    fun testUpdateNullContext() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun update(id: Long, payload: UpdatePayloadTest, context: IContext?): Boolean = true
        }
        assertEquals(
            true,
            repository.update(1, UpdatePayloadTest("test"))
        )
    }

    @Test
    fun testUpdateUnsupported() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.update(1, UpdatePayloadTest("test"), Unit)
        }
    }

    @Test
    fun testDelete() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun delete(id: Long, context: IContext?): Boolean = true
        }
        assertEquals(
            true,
            repository.delete(1, Unit)
        )
    }

    @Test
    fun testDeleteNullContext() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override fun delete(id: Long, context: IContext?): Boolean = true
        }
        assertEquals(
            true,
            repository.delete(1)
        )
    }

    @Test
    fun testDeleteUnsupported() {
        val repository = object : IModelRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.delete(1, Unit)
        }
    }

}
