package dev.kaccelero.usecases

interface IUnitSuspendUseCase<Output> : IGenericUseCase {

    suspend operator fun invoke(): Output

}
