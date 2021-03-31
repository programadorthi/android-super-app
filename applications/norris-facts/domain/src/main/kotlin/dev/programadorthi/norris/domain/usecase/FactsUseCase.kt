package dev.programadorthi.norris.domain.usecase

import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch
import dev.programadorthi.norris.domain.repository.FactsRepository
import dev.programadorthi.shared.domain.Result

interface FactsUseCase {
    suspend fun categories(limit: Int, shuffle: Boolean): Result<List<Category>>
    suspend fun lastSearches(): Result<List<LastSearch>>
    suspend fun search(text: String): Result<List<Fact>>

    companion object Instance {
        operator fun invoke(
            factsRepository: FactsRepository
        ): FactsUseCase = FactsUseCaseImpl(factsRepository)
    }
}
