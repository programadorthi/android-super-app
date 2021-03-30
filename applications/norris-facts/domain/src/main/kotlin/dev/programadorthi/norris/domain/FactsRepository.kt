package dev.programadorthi.norris.domain

import dev.programadorthi.shared.domain.Result

interface FactsRepository {
    suspend fun fetchCategories(limit: Int, shuffle: Boolean): Result<List<String>>
    suspend fun getLastSearches(): Result<List<String>>
    suspend fun doSearch(text: String): Result<List<Fact>>
}
