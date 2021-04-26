package dev.programadorthi.norris.domain.usecase

import dev.programadorthi.norris.domain.FactsBusiness
import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch
import dev.programadorthi.norris.domain.repository.FactsRepository
import dev.programadorthi.shared.domain.Result

internal class FactsUseCaseImpl(
    private val factsRepository: FactsRepository
) : FactsUseCase {

    override suspend fun categories(
        limit: Int,
        shuffle: Boolean
    ): Result<List<Category>> = when {
        limit <= MIN_OFFSET -> Result.success(emptyList())
        else -> factsRepository.fetchCategories(limit, shuffle)
    }

    override suspend fun lastSearches(): Result<List<LastSearch>> =
        factsRepository.getLastSearches()

    override suspend fun search(text: String): Result<List<Fact>> =
        when {
            text.isBlank() -> Result.business(FactsBusiness.EmptySearch)
            else -> factsRepository.doSearch(text)
        }

    private companion object {
        private const val MIN_OFFSET = 0
    }
}
