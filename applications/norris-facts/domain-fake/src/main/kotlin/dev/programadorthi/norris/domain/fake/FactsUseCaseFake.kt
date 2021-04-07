package dev.programadorthi.norris.domain.fake

import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.shared.domain.Result

class FactsUseCaseFake : FactsUseCase {
    private val categories = mutableListOf<Category>()
    private val facts = mutableListOf<Fact>()
    private val lastSearches = mutableListOf<LastSearch>()

    var exceptionResult: Throwable? = null

    override suspend fun categories(
        limit: Int,
        shuffle: Boolean
    ): Result<List<Category>> {
        if (limit <= 0) return mapResult(emptyList())
        val shuffled = if (shuffle) categories.shuffled() else categories
        return mapResult(shuffled)
    }

    override suspend fun lastSearches(): Result<List<LastSearch>> =
        mapResult(lastSearches)

    override suspend fun search(text: String): Result<List<Fact>> =
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
