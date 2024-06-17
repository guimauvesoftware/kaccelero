package dev.kaccelero.commons.sessions

import dev.kaccelero.database.IDatabase
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseTest(
    name: String,
) : IDatabase {

    private val database: Database = Database.connect(
        "jdbc:h2:mem:$name;DB_CLOSE_DELAY=-1;", "org.h2.Driver"
    )

    override fun <T> transaction(statement: Transaction.() -> T): T = transaction(database, statement)

    override suspend fun <T> suspendedTransaction(statement: suspend Transaction.() -> T): T =
        newSuspendedTransaction(Dispatchers.IO, database) { statement() }

}
