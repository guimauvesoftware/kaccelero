package dev.kaccelero.plugins

import dev.kaccelero.commons.localization.ITranslateUseCase
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.util.*
import java.util.*

/**
 * Internationalization support feature for Ktor.
 *
 * @see Configuration for how to configure this feature
 */
class I18n private constructor(configuration: I18nConfiguration) : ITranslateUseCase by configuration.translateUseCase {

    val supportedLocales = configuration.supportedLocales
    val defaultLocale = configuration.defaultLocale ?: supportedLocales.first()
    val getLocaleForCallUseCase = configuration.getLocaleForCallUseCase
    val useOfCookie = configuration.useOfCookie
    val useOfUri = configuration.useOfUri
    val localeCookieName = configuration.cookieName
    val supportedPathPrefixes = "(${supportedLocales.joinToString("|", transform = { it.language })})".toRegex()

    init {
        Locale.setDefault(defaultLocale)
    }

    companion object Plugin : BaseApplicationPlugin<ApplicationCallPipeline, I18nConfiguration, I18n> {

        override val key = AttributeKey<I18n>("I18n")

        override fun install(pipeline: ApplicationCallPipeline, configure: I18nConfiguration.() -> Unit): I18n {
            val configuration = I18nConfiguration().apply { configure() }.apply {
                require(supportedLocales.isNotEmpty()) { "Supported locales must not be empty" }
                require(defaultLocale == null || defaultLocale!! in supportedLocales) {
                    "Default locale must be one of the supported locales"
                }
            }
            return I18n(configuration)
        }
    }
}

val Application.i18n
    get() = this.plugin(I18n)
