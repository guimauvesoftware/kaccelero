package dev.kaccelero.commons.analytics

import dev.kaccelero.usecases.IPairSuspendUseCase

interface ILogEventSuspendUseCase :
    IPairSuspendUseCase<IAnalyticsEventName, Map<IAnalyticsEventParameter, IAnalyticsEventValue>, Unit>
