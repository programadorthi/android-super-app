package dev.programadorthi.norris.domain.fake.data

import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch
import dev.programadorthi.norris.domain.repository.FactsRepository
import dev.programadorthi.shared.domain.Result

class FactsRepositoryFake : FactsRepository {
    private val categories = mutableListOf<Category>()
    private val facts = mutableListOf<Fact>()
    private val lastSearches = mutableListOf<LastSearch>()

    var exceptionResult: Throwable? = null

    override suspend fun fetchCategories(
        limit: Int,
        shuffle: Boolean
    ): Result<List<Category>> {
        val result = categories.take(limit)
        val shuffled = if (shuffle) result.shuffled() else result
        return mapResult(shuffled)
    }

    override suspend fun getLastSearches(): Result<List<LastSearch>> =
        mapResult(lastSearches)

    override suspend fun doSearch(text: String): Result<List<Fact>> =
        mapResult(facts)

    fun addCategory(vararg categories: Category) {
        this.categories += resultOrThrow(categories)
    }

    fun addFact(vararg facts: Fact) {
        this.facts += resultOrThrow(facts)
    }

    fun addLastSearch(search: LastSearch) {
        lastSearches += resultOrThrow(search)
    }

    private fun <T> mapResult(data: T) = when {
        exceptionResult != null -> Result.failure(exceptionResult!!)
        else -> Result.success(data)
    }

    private fun <T> resultOrThrow(data: T) = when {
        exceptionResult != null -> throw exceptionResult!!
        else -> data
    }
}
