package dev.kaccelero.commons.exceptions

import dev.kaccelero.usecases.ISuspendUseCase

interface ICaptureExceptionSuspendUseCase : ISuspendUseCase<Throwable, Unit>
