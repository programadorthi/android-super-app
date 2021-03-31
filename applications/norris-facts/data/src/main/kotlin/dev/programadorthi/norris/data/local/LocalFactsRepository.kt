package dev.programadorthi.norris.data.local

import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch

interface LocalFactsRepository {
    suspend fun getCategories(): List<String>
    suspend fun getFacts(): List<Fact>
    suspend fun getLastSearches(): List<String>
    suspend fun saveCategories(categories: List<Category>)
    suspend fun saveFacts(facts: List<Fact>)
    suspend fun saveNewSearch(lastSearch: LastSearch)
}
