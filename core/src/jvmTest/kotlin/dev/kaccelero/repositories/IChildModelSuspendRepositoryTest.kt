package dev.kaccelero.repositories

import dev.kaccelero.mocks.CreatePayloadTest
import dev.kaccelero.mocks.ModelTest
import dev.kaccelero.mocks.UpdatePayloadTest
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertFailsWith

class IChildModelSuspendRepositoryTest {

    @Test
    fun testListUnsupported(): Unit = runBlocking {
        val repository =
            object : IChildModelSuspendRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest, Unit> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.list(Unit)
        }
    }

    @Test
    fun testListLimitOffsetUnsupported(): Unit = runBlocking {
        val repository =
            object : IChildModelSuspendRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest, Unit> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.list(Pagination(1, 0), Unit)
        }
    }

    @Test
    fun testCountUnsupported(): Unit = runBlocking {
        val repository =
            object : IChildModelSuspendRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest, Unit> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.count(Unit)
        }
    }

    @Test
    fun testGetUnsupported(): Unit = runBlocking {
        val repository =
            object : IChildModelSuspendRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest, Unit> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.get(1, Unit)
        }
    }

    @Test
    fun testCreateUnsupported(): Unit = runBlocking {
        val repository =
            object : IChildModelSuspendRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest, Unit> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.create(CreatePayloadTest("test"), Unit)
        }
    }

    @Test
    fun testUpdateUnsupported(): Unit = runBlocking {
        val repository =
            object : IChildModelSuspendRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest, Unit> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.update(1, UpdatePayloadTest("test"), Unit)
        }
    }

    @Test
    fun testDeleteUnsupported(): Unit = runBlocking {
        val repository =
            object : IChildModelSuspendRepository<ModelTest, Long, CreatePayloadTest, UpdatePayloadTest, Unit> {}
        assertFailsWith(UnsupportedOperationException::class) {
            repository.delete(1, Unit)
        }
    }

}
