package dev.kaccelero.commons.emails

import dev.kaccelero.usecases.IPairUseCase

interface ISendEmailUseCase : IPairUseCase<IEmail, List<String>, Unit>
