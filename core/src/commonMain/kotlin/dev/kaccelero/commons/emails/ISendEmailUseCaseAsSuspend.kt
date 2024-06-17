package dev.kaccelero.commons.emails

class ISendEmailUseCaseAsSuspend(
    private val useCase: ISendEmailUseCase,
) : ISendEmailSuspendUseCase {

    override suspend fun invoke(input1: IEmail, input2: List<String>) = useCase(input1, input2)

}
