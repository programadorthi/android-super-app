package dev.programadorthi.norris.domain

import dev.programadorthi.shared.domain.Result

internal class FactsUseCaseImpl(
    private val factsRepository: FactsRepository
) : FactsUseCase {

    override suspend fun categories(
        limit: Int,
        shuffle: Boolean
    ): Result<List<String>> = when {
        limit <= MIN_OFFSET -> Result.success(emptyList())
        else -> factsRepository.fetchCategories(limit, shuffle)
    }

    override suspend fun lastSearches(): Result<List<String>> =
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
