package dev.kaccelero.commons.analytics

import kotlin.js.JsExport

@JsExport
data class AnalyticsEventValue<T>(val value: T) : IAnalyticsEventValue
