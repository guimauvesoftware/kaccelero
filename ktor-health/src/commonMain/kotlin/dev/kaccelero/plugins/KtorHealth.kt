package dev.kaccelero.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.*

class KtorHealth private constructor(
    val config: Configuration,
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

    class Configuration internal constructor() {

        var checks: Map<String, Map<String, HealthCheck>> = emptyMap()
            private set
        var noHealth = false
            private set
        var noReady = false
            private set

        internal fun getChecksWithFunctions(): Map<String, suspend () -> Map<String, Boolean>> =
            checks.mapValues { (_, v) ->
                {
                    v.mapValues { it.value() }
                }
            }

        private fun ensureDisableUnambiguous(url: String) {
            checks[url]?.let {
                if (it.isNotEmpty()) throw AssertionError("Cannot disable a check which has been assigned functions")
            }
        }

        /**
         * Calling this disables the default health check on /healthz
         */
        fun disableHealthCheck() {
            noHealth = true
            ensureDisableUnambiguous("healthz")
        }

        /**
         * Calling this disabled the default ready check on /readyz
         */
        fun disableReadyCheck() {
            noReady = true
            ensureDisableUnambiguous("readyz")
        }

        private fun getCheck(url: String) = checks.getOrElse(url) {
            mutableMapOf<String, suspend () -> Boolean>().also {
                checks = checks + (url to it)
            }
        }

        /**
         * Adds a check function to a custom check living at the specified URL
         */
        fun customCheck(url: String, name: String, check: HealthCheck) {
            (getCheck(url.trim('/')) as MutableMap<String, suspend () -> Boolean>)[name] = check
        }

        /**
         * Add a health check giving it a name
         */
        fun healthCheck(name: String, check: HealthCheck) = customCheck("healthz", name, check)

        /**
         * Add a ready check giving it a name
         */
        fun readyCheck(name: String, check: HealthCheck) = customCheck("readyz", name, check)

        internal fun ensureWellKnown() {
            if (!noHealth) getCheck("healthz")
            if (!noReady) getCheck("readyz")
        }

    }

    companion object Feature : BaseApplicationPlugin<ApplicationCallPipeline, Configuration, KtorHealth> {

        override val key = AttributeKey<KtorHealth>("KtorHealth")
        override fun install(pipeline: ApplicationCallPipeline, configure: Configuration.() -> Unit) =
            KtorHealth(
                Configuration()
                    .apply(configure)
                    .apply { ensureWellKnown() }
            ).apply {
                addInterceptor(pipeline)
            }

    }

}
