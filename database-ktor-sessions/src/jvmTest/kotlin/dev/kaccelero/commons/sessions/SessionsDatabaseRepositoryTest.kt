package dev.kaccelero.commons.sessions

import dev.kaccelero.database.IDatabase
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.selectAll
import kotlin.test.Test
import kotlin.test.assertEquals

class SessionsDatabaseRepositoryTest {

    private fun createDatabase(name: String): IDatabase = DatabaseTest(name)

    @Test
    fun testWriteNewSession() = runBlocking {
        val database = createDatabase("testWriteNewSession")
        val repository = SessionsDatabaseRepository(database)
        repository.write("id", "value")
        val session = database.suspendedTransaction {
            Sessions.selectAll().map(Sessions::toSession).singleOrNull()
        }
        assertEquals("id", session?.id)
        assertEquals("value", session?.value)
    }

    @Test
    fun testWriteExistingSession() = runBlocking {
        val database = createDatabase("testWriteExistingSession")
        val repository = SessionsDatabaseRepository(database)
        repository.write("id", "value")
        repository.write("id", "value2")
        val session = database.suspendedTransaction {
            Sessions.selectAll().map(Sessions::toSession).singleOrNull()
        }
        assertEquals("id", session?.id)
        assertEquals("value2", session?.value)
    }

    @Test
    fun testReadSession() = runBlocking {
        val database = createDatabase("testReadSession")
        val repository = SessionsDatabaseRepository(database)
        repository.write("id", "value")
        val value = repository.read("id")
        assertEquals("value", value)
    }

    @Test
    fun testInvalidateSession() = runBlocking {
        val database = createDatabase("testInvalidateSession")
        val repository = SessionsDatabaseRepository(database)
        repository.write("id", "value")
        repository.invalidate("id")
        val count = database.suspendedTransaction {
            Sessions.selectAll().count()
        }
        assertEquals(0, count)
    }

}
