package dev.kaccelero.commons.auth

import dev.kaccelero.client.IAPIClient
import dev.kaccelero.usecases.ISuspendUseCase

interface IRenewTokenUseCase : ISuspendUseCase<IAPIClient, Boolean>
