package dev.programadorthi.norris.domain.data.remote.repository

import dev.programadorthi.norris.domain.data.RemoteFactsRepository
import dev.programadorthi.norris.domain.data.remote.FactsService
import dev.programadorthi.norris.domain.data.remote.raw.FactsResponseRaw
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.shared.network.manager.NetworkManager
import dev.programadorthi.shared.network.mapper.RemoteMapper

object RemoteFactsRepositoryFactory {
    operator fun invoke(
        factsMapper: RemoteMapper<FactsResponseRaw, List<Fact>>,
        factsService: FactsService,
        networkManager: NetworkManager
    ): RemoteFactsRepository = RemoteFactsRepositoryImpl(
        factsMapper = factsMapper,
        factsService = factsService,
        networkManager = networkManager
    )
}
