package dev.kaccelero.commons.exceptions

class ICaptureExceptionUseCaseAsSuspend(
    private val useCase: ICaptureExceptionUseCase,
) : ICaptureExceptionSuspendUseCase {

    override suspend fun invoke(input: Throwable) = useCase(input)

}
