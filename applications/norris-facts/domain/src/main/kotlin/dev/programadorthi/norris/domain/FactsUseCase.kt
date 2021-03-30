package dev.programadorthi.norris.domain

import dev.programadorthi.shared.domain.Result

interface FactsUseCase {
    suspend fun categories(limit: Int, shuffle: Boolean): Result<List<String>>
    suspend fun lastSearches(): Result<List<String>>
    suspend fun search(text: String): Result<List<Fact>>

    companion object Instance {
        operator fun invoke(
            factsRepository: FactsRepository
        ): FactsUseCase = FactsUseCaseImpl(factsRepository)
    }
}
