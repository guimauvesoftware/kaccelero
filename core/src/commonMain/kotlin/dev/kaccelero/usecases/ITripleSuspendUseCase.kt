package dev.kaccelero.usecases

interface ITripleSuspendUseCase<Input1, Input2, Input3, Output> : IGenericUseCase {

    suspend operator fun invoke(input1: Input1, input2: Input2, input3: Input3): Output

}
