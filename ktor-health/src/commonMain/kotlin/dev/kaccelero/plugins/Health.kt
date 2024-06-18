package dev.kaccelero.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.*

class Health private constructor(
    val config: HealthConfiguration,
) {

    fun addInterceptor(pipeline: ApplicationCallPipeline) {
        val checks = config.getChecksWithFunctions().takeIf { it.isNotEmpty() } ?: return
        pipeline.intercept(ApplicationCallPipeline.Call) {
            val check = checks[call.request.path().trim('/')]?.invoke() ?: return@intercept
            val success = check.values.all { it }
            call.respond(
                if (success) HttpStatusCode.OK else HttpStatusCode.ServiceUnavailable,
                check
            )
            finish()
        }
    }

    companion object Feature : BaseApplicationPlugin<ApplicationCallPipeline, HealthConfiguration, Health> {

        override val key = AttributeKey<Health>("KtorHealth")
        override fun install(pipeline: ApplicationCallPipeline, configure: HealthConfiguration.() -> Unit) =
            Health(
                HealthConfiguration()
                    .apply(configure)
                    .apply { ensureWellKnown() }
            ).apply {
                addInterceptor(pipeline)
            }

    }

}
