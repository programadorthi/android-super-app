package dev.programadorthi.norris.local

internal class LocalFactsRepositoryImpl(
    private val database: NorrisQueries
) : LocalFactsRepository {

    override suspend fun getCategories(): List<String> =
        database.selectCategories().executeAsList()

    override suspend fun getFacts(): List<Facts> =
        database.selectFacts().executeAsList()

    override suspend fun getLastSearches(): List<String> =
        database.selectLastSearches().executeAsList()

    override suspend fun saveCategories(categories: List<Categories>) {
        database.transaction {
            categories.forEach(database::insertCategory)
        }
    }

    override suspend fun saveFacts(facts: List<Facts>) {
        database.transaction {
            facts.forEach(database::insertFacts)
        }
    }

    override suspend fun saveNewSearch(lastSearch: LastSearch) {
        database.insertLastSearch(lastSearch)
    }
}
