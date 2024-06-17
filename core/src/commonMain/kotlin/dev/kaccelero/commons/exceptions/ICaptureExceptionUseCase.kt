package dev.kaccelero.commons.exceptions

import dev.kaccelero.usecases.IUseCase
import kotlin.js.JsExport

@JsExport
interface ICaptureExceptionUseCase : IUseCase<Throwable, Unit>
