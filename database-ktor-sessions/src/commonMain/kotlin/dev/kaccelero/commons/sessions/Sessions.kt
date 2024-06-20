package dev.kaccelero.commons.sessions

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Sessions : Table() {

    val id = varchar("id", 255)
    val value = text("value")

    override val primaryKey = PrimaryKey(id)

    fun toSession(
        row: ResultRow,
    ) = Session(
        row[id],
        row[value],
    )

}
