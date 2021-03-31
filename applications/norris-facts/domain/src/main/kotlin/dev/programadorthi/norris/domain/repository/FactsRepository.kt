package dev.programadorthi.norris.domain.repository

import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch
import dev.programadorthi.shared.domain.Result

interface FactsRepository {
    suspend fun fetchCategories(limit: Int, shuffle: Boolean): Result<List<Category>>
    suspend fun getLastSearches(): Result<List<LastSearch>>
    suspend fun doSearch(text: String): Result<List<Fact>>
}
