package dev.kaccelero.usecases

import kotlin.js.JsExport

@JsExport
interface IUnitUseCase<Output> : IGenericUseCase {

    operator fun invoke(): Output

}
