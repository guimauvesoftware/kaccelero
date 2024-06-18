package dev.kaccelero.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import dev.kaccelero.models.IContext
import dev.kaccelero.models.RecursiveId
import dev.kaccelero.models.UnitModel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class IModelRemoteRepositoryTest {

    @Test
    fun testList() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun list(context: IContext?): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(
            listOf(ModelTest(1, "test")),
            repository.list(RecursiveId<UnitModel, Unit, Unit>(Unit))
        )
    }

    @Test
    fun testListNullContext() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun list(context: IContext?): List<ModelTest> = listOf(ModelTest(1, "test"))
        }
        assertEquals(
            listOf(ModelTest(1, "test")),
            repository.list()
        )
    }

    @Test
    fun testListUnsupported(): Unit = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.list(RecursiveId<UnitModel, Unit, Unit>(Unit))
        }
    }

    @Test
    fun testListLimitOffset() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun list(pagination: Pagination, context: IContext?): List<ModelTest> =
                listOf(ModelTest(1, "test"))
        }
        assertEquals(
            listOf(ModelTest(1, "test")),
            repository.list(Pagination(1, 0), RecursiveId<UnitModel, Unit, Unit>(Unit))
        )
    }

    @Test
    fun testListLimitOffsetNullContext() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun list(pagination: Pagination, context: IContext?): List<ModelTest> =
                listOf(ModelTest(1, "test"))
        }
        assertEquals(
            listOf(ModelTest(1, "test")),
            repository.list(Pagination(1, 0))
        )
    }

    @Test
    fun testListLimitOffsetUnsupported(): Unit = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.list(Pagination(1, 0), RecursiveId<UnitModel, Unit, Unit>(Unit))
        }
    }

    @Test
    fun testCount() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun count(context: IContext?): Long = 1
        }
        assertEquals(
            1,
            repository.count(RecursiveId<UnitModel, Unit, Unit>(Unit))
        )
    }

    @Test
    fun testCountNullContext() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun count(context: IContext?): Long = 1
        }
        assertEquals(
            1,
            repository.count()
        )
    }

    @Test
    fun testCountUnsupported(): Unit = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.count(RecursiveId<UnitModel, Unit, Unit>(Unit))
        }
    }

    @Test
    fun testGet() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun get(id: Long, context: IContext?): ModelTest = ModelTest(1, "test")
        }
        assertEquals(
            ModelTest(1, "test"),
            repository.get(1, RecursiveId<UnitModel, Unit, Unit>(Unit))
        )
    }

    @Test
    fun testGetNullContext() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun get(id: Long, context: IContext?): ModelTest = ModelTest(1, "test")
        }
        assertEquals(
            ModelTest(1, "test"),
            repository.get(1)
        )
    }

    @Test
    fun testGetUnsupported(): Unit = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.get(1, RecursiveId<UnitModel, Unit, Unit>(Unit))
        }
    }

    @Test
    fun testCreate() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun create(payload: CreatePayloadTest, context: IContext?): ModelTest =
                ModelTest(1, payload.value)
        }
        assertEquals(
            ModelTest(1, "test"),
            repository.create(CreatePayloadTest("test"), RecursiveId<UnitModel, Unit, Unit>(Unit))
        )
    }

    @Test
    fun testCreateNullContext() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun create(payload: CreatePayloadTest, context: IContext?): ModelTest =
                ModelTest(1, payload.value)
        }
        assertEquals(
            ModelTest(1, "test"),
            repository.create(CreatePayloadTest("test"))
        )
    }

    @Test
    fun testCreateUnsupported(): Unit = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.create(CreatePayloadTest("test"), RecursiveId<UnitModel, Unit, Unit>(Unit))
        }
    }

    @Test
    fun testUpdate() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun update(id: Long, payload: UpdatePayloadTest, context: IContext?): ModelTest =
                ModelTest(1, payload.value)
        }
        assertEquals(
            ModelTest(1, "test"),
            repository.update(1, UpdatePayloadTest("test"), RecursiveId<UnitModel, Unit, Unit>(Unit))
        )
    }

    @Test
    fun testUpdateNullContext() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun update(id: Long, payload: UpdatePayloadTest, context: IContext?): ModelTest =
                ModelTest(1, payload.value)
        }
        assertEquals(
            ModelTest(1, "test"),
            repository.update(1, UpdatePayloadTest("test"))
        )
    }

    @Test
    fun testUpdateUnsupported(): Unit = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.update(1, UpdatePayloadTest("test"), RecursiveId<UnitModel, Unit, Unit>(Unit))
        }
    }

    @Test
    fun testDelete() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun delete(id: Long, context: IContext?): Boolean = true
        }
        assertEquals(
            true,
            repository.delete(1, RecursiveId<UnitModel, Unit, Unit>(Unit))
        )
    }

    @Test
    fun testDeleteNullContext() = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {
            override suspend fun delete(id: Long, context: IContext?): Boolean = true
        }
        assertEquals(
            true,
            repository.delete(1)
        )
    }

    @Test
    fun testDeleteUnsupported(): Unit = runBlocking {
        val repository = object : IModelRemoteRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.delete(1, RecursiveId<UnitModel, Unit, Unit>(Unit))
        }
    }

}
