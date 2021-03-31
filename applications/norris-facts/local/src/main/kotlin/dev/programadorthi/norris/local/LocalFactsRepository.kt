package dev.programadorthi.norris.local

interface LocalFactsRepository {
    suspend fun getCategories(): List<String>
    suspend fun getFacts(): List<Facts>
    suspend fun getLastSearches(): List<String>
    suspend fun saveCategories(categories: List<Categories>)
    suspend fun saveFacts(facts: List<Facts>)
    suspend fun saveNewSearch(lastSearch: LastSearch)

    companion object Instance {
        operator fun invoke(
            database: NorrisQueries
        ): LocalFactsRepository = LocalFactsRepositoryImpl(database)
    }
}
