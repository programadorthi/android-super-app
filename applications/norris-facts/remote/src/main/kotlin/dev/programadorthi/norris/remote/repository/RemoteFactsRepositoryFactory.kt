package dev.programadorthi.norris.remote.repository

import dev.programadorthi.norris.data.remote.RemoteFactsRepository
import dev.programadorthi.norris.remote.FactsService
import dev.programadorthi.norris.remote.mapper.FactsMapper
import dev.programadorthi.shared.network.manager.NetworkManager

object RemoteFactsRepositoryFactory {
    operator fun invoke(
        factsService: FactsService,
        networkManager: NetworkManager
    ): RemoteFactsRepository = RemoteFactsRepositoryImpl(
        factsMapper = FactsMapper,
        factsService = factsService,
        networkManager = networkManager
    )
}
