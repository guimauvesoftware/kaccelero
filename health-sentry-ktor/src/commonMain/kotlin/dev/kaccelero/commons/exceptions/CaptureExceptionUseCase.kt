package dev.kaccelero.commons.exceptions

import io.sentry.Sentry

class CaptureExceptionUseCase : ICaptureExceptionUseCase {

    override fun invoke(input: Throwable) {
        Sentry.captureException(input)
    }

}
