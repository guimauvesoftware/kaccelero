package dev.kaccelero.usecases

interface ISuspendUseCase<Input, Output> : IGenericUseCase {

    suspend operator fun invoke(input: Input): Output

}
