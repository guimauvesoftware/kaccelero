package dev.kaccelero.usecases

interface IPairSuspendUseCase<Input1, Input2, Output> : IGenericUseCase {

    suspend operator fun invoke(input1: Input1, input2: Input2): Output

}
