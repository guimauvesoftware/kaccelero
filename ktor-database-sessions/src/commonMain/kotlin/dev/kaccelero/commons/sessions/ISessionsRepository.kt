package dev.kaccelero.commons.sessions

import dev.kaccelero.repositories.IModelSuspendRepository
import io.ktor.server.sessions.*

interface ISessionsRepository : IModelSuspendRepository<Session, String, Session, String>, SessionStorage
