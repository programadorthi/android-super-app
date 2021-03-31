package dev.programadorthi.norris.remote.repository

import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.remote.FactsService
import dev.programadorthi.norris.remote.mapper.FactsMapper
import dev.programadorthi.shared.network.manager.NetworkManager

interface RemoteFactsRepository {
    suspend fun fetchCategories(): List<String>
    suspend fun search(text: String): List<Fact>

    companion object Instance {
        operator fun invoke(
            factsService: FactsService,
            networkManager: NetworkManager
        ): RemoteFactsRepository = RemoteFactsRepositoryImpl(
            factsMapper = FactsMapper,
            factsService = factsService,
            networkManager = networkManager
        )
    }
}
