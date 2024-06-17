package dev.kaccelero.commons.permissions

class ICheckPermissionUseCaseAsSuspend(
    private val useCase: ICheckPermissionUseCase,
) : ICheckPermissionSuspendUseCase {

    override suspend fun invoke(input1: IPermittee, input2: IPermission): Boolean = useCase(input1, input2)

}
