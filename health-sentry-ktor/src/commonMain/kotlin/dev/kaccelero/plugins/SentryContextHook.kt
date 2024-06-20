package dev.kaccelero.plugins

import io.ktor.server.application.*
import io.ktor.util.pipeline.*
import io.sentry.kotlin.SentryContext
import kotlinx.coroutines.withContext

object SentryContextHook : Hook<suspend (suspend () -> Unit) -> Unit> {

    private val phase = PipelinePhase("SentryContext")

    override fun install(
        pipeline: ApplicationCallPipeline,
        handler: suspend (suspend () -> Unit) -> Unit,
    ) {
        pipeline.insertPhaseBefore(ApplicationCallPipeline.Setup, phase)
        pipeline.intercept(phase) {
            withContext(SentryContext()) {
                handler(::proceed)
            }
        }
    }

}
