package dev.programadorthi.norris.local

import dev.programadorthi.norris.data.local.LocalFactsRepository
import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch
import dev.programadorthi.shared.database.Categories
import dev.programadorthi.shared.database.Facts
import dev.programadorthi.shared.database.NorrisQueries
import dev.programadorthi.shared.database.LastSearch as DBLastSearch

internal class LocalFactsRepositoryImpl(
    private val database: NorrisQueries
) : LocalFactsRepository {

    override suspend fun getCategories(): List<String> =
        database.selectCategories().executeAsList()

    override suspend fun getFacts(): List<Fact> =
        database.selectFacts().executeAsList()
            .map { fact ->
                Fact(
                    id = fact.id,
                    url = fact.url,
                    value = fact.text,
                    categories = emptyList() // TODO: get category from INNER JOIN
                )
            }

    override suspend fun getLastSearches(): List<String> =
        database.selectLastSearches().executeAsList()

    override suspend fun saveCategories(categories: List<Category>) {
        database.transaction {
            categories
                .map { category -> Categories(name = category.name) }
                .forEach(database::insertCategory)
        }
    }

    override suspend fun saveFacts(facts: List<Fact>) {
        database.transaction {
            facts
                .map { fact ->
                    Facts(
                        id = fact.id,
                        text = fact.value,
                        url = fact.url
                    )
                }
                .forEach(database::insertFacts)
        }
    }

    override suspend fun saveNewSearch(lastSearch: LastSearch) {
        database.insertLastSearch(DBLastSearch(term = lastSearch.term))
    }
}
