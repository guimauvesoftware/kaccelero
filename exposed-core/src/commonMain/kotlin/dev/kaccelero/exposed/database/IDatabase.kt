package dev.kaccelero.exposed.database

import org.jetbrains.exposed.sql.Transaction

interface IDatabase {

    fun <T> transaction(statement: Transaction.() -> T): T

    suspend fun <T> suspendedTransaction(statement: suspend Transaction.() -> T): T

}
