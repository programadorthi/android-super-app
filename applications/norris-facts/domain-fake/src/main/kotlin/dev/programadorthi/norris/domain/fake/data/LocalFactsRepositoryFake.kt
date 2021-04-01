package dev.programadorthi.norris.domain.fake.data

import dev.programadorthi.norris.domain.data.LocalFactsRepository
import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch

class LocalFactsRepositoryFake : LocalFactsRepository {
    private val categories = mutableListOf<Category>()
    private val facts = mutableListOf<Fact>()
    private val lastSearches = mutableListOf<LastSearch>()

    var exceptionToThrow: Throwable? = null

    override suspend fun getCategories(): List<String> =
        resultOrThrow(categories.map { category -> category.name })

    override suspend fun getFacts(): List<Fact> =
        resultOrThrow(facts)

    override suspend fun getLastSearches(): List<String> =
        resultOrThrow(lastSearches.map { search -> search.term })

    override suspend fun saveCategories(categories: List<Category>) {
        this.categories += resultOrThrow(categories)
    }

    override suspend fun saveFacts(facts: List<Fact>) {
        this.facts += resultOrThrow(facts)
    }

    override suspend fun saveNewSearch(lastSearch: LastSearch) {
        lastSearches += resultOrThrow(lastSearch)
    }

    private fun <T> resultOrThrow(data: T) = when {
        exceptionToThrow != null -> throw exceptionToThrow!!
        else -> data
    }
}
