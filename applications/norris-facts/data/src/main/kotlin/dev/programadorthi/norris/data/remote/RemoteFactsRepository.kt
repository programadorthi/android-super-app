package dev.programadorthi.norris.data.remote

import dev.programadorthi.norris.domain.model.Fact

interface RemoteFactsRepository {
    suspend fun fetchCategories(): List<String>
    suspend fun search(text: String): List<Fact>
}
