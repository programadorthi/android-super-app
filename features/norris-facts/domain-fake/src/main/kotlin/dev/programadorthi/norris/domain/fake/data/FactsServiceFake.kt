package dev.programadorthi.norris.domain.fake.data

import dev.programadorthi.norris.domain.data.remote.FactsService
import dev.programadorthi.norris.domain.data.remote.raw.FactsResponseRaw

// FIXME: how to avoid Service and Raws be public?
class FactsServiceFake : FactsService {
    private val categories = mutableListOf<String>()

    lateinit var raw: FactsResponseRaw
    var exceptionToThrow: Throwable? = null

    override suspend fun fetchCategories(): List<String> =
        resultOrThrow(categories)

    override suspend fun search(query: String): FactsResponseRaw =
        resultOrThrow(raw)

    fun addCategories(vararg categories: String) {
        this.categories += resultOrThrow(categories)
    }

    private fun <T> resultOrThrow(data: T) = when {
        exceptionToThrow != null -> throw exceptionToThrow!!
        else -> data
    }
}
