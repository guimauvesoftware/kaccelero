package dev.kaccelero.commons.localization

import dev.kaccelero.usecases.IUseCase
import io.ktor.server.application.*

interface IGetLocaleForCallUseCase : IUseCase<ApplicationCall, Locale>
