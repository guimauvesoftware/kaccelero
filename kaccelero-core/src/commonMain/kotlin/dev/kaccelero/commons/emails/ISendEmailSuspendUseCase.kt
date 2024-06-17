package dev.kaccelero.commons.emails

import dev.kaccelero.usecases.IPairSuspendUseCase

interface ISendEmailSuspendUseCase : IPairSuspendUseCase<IEmail, List<String>, Unit>
