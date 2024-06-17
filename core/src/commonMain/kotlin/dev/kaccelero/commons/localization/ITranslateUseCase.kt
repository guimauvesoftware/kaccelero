package dev.kaccelero.commons.localization

import dev.kaccelero.usecases.ITripleUseCase

//@JsExport
interface ITranslateUseCase : ITripleUseCase<Locale, String, List<String>, String> {

    operator fun invoke(locale: Locale, key: String): String = invoke(locale, key, listOf())

}
