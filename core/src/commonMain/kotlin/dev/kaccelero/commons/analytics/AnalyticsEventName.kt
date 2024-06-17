package dev.kaccelero.commons.analytics

import kotlin.js.JsExport

@JsExport
data class AnalyticsEventName(val name: String) : IAnalyticsEventName
