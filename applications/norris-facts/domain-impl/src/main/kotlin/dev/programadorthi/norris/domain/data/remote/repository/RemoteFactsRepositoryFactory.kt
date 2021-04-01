package dev.programadorthi.norris.domain.data.remote.repository

import dev.programadorthi.norris.domain.data.RemoteFactsRepository
import dev.programadorthi.norris.domain.data.remote.FactsService
import dev.programadorthi.norris.domain.data.remote.mapper.FactsMapper
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
