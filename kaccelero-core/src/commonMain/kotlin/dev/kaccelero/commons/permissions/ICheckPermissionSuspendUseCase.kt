package dev.kaccelero.commons.permissions

import dev.kaccelero.usecases.IPairSuspendUseCase

interface ICheckPermissionSuspendUseCase : IPairSuspendUseCase<IPermittee, IPermission, Boolean>
