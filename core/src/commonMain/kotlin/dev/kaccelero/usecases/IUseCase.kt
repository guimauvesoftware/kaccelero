package dev.kaccelero.usecases

import kotlin.js.JsExport

@JsExport
interface IUseCase<Input, Output> : IGenericUseCase {

    operator fun invoke(input: Input): Output

}
