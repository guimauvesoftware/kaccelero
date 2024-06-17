package dev.kaccelero.commons.analytics

import dev.kaccelero.usecases.IPairUseCase
import kotlin.js.JsExport

@JsExport
interface ILogEventUseCase :
    IPairUseCase<IAnalyticsEventName, Map<IAnalyticsEventParameter, IAnalyticsEventValue>, Unit>
