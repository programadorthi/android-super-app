package dev.programadorthi.norris.data

import dev.programadorthi.norris.domain.repository.FactsRepository
import dev.programadorthi.norris.local.LocalFactsRepository
import dev.programadorthi.norris.remote.repository.RemoteFactsRepository
import kotlinx.coroutines.CoroutineDispatcher

interface FactsRepository : FactsRepository {
    companion object Instance {
        operator fun invoke(
            localFactsRepository: LocalFactsRepository,
            remoteFactsRepository: RemoteFactsRepository,
            ioDispatcher: CoroutineDispatcher
        ): FactsRepository = FactsRepositoryImpl(
            localFactsRepository = localFactsRepository,
            remoteFactsRepository = remoteFactsRepository,
            ioDispatcher = ioDispatcher
        )
    }
}
