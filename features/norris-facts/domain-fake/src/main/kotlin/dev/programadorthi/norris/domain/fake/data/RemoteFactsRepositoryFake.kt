package dev.programadorthi.norris.domain.fake.data

import dev.programadorthi.norris.domain.data.RemoteFactsRepository
import dev.programadorthi.norris.domain.model.Fact

class RemoteFactsRepositoryFake : RemoteFactsRepository {
    private val categories = mutableListOf<String>()
    private val facts = mutableListOf<Fact>()

    var exceptionToThrow: Throwable? = null

    override suspend fun fetchCategories(): List<String> =
        resultOrThrow(categories)

    override suspend fun search(text: String): List<Fact> =
        resultOrThrow(facts)

    fun addCategory(vararg categories: String) {
        this.categories += resultOrThrow(categories)
    }

    fun addFact(vararg facts: Fact) {
        this.facts += resultOrThrow(facts)
    }

    private fun <T> resultOrThrow(data: T) = when {
        exceptionToThrow != null -> throw exceptionToThrow!!
        else -> data
    }
}
