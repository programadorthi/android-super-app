package dev.programadorthi.norris.remote.repository

import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.remote.FactsService
import dev.programadorthi.norris.remote.raw.FactsResponseRaw
import dev.programadorthi.shared.network.manager.NetworkManager
import dev.programadorthi.shared.network.mapper.RemoteMapper

internal class RemoteFactsRepositoryImpl(
    private val factsMapper: RemoteMapper<FactsResponseRaw, List<Fact>>,
    private val factsService: FactsService,
    private val networkManager: NetworkManager
) : RemoteFactsRepository {

    override suspend fun fetchCategories(): List<String> =
        networkManager.execute {
            return@execute factsService.fetchCategories()
        }

    override suspend fun search(text: String): List<Fact> =
        networkManager.execute {
            val result = factsService.search(query = text)
            return@execute factsMapper.invoke(result)
        }
}
