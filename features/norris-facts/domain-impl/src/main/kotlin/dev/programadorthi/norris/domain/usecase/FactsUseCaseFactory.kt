package dev.programadorthi.norris.domain.usecase

import dev.programadorthi.norris.domain.repository.FactsRepository

object FactsUseCaseFactory {
    operator fun invoke(
        factsRepository: FactsRepository
    ): FactsUseCase = FactsUseCaseImpl(factsRepository)
}
