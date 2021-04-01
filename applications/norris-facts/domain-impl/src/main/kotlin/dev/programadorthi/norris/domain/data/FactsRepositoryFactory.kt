package dev.programadorthi.norris.domain.data

import dev.programadorthi.norris.domain.repository.FactsRepository
import kotlinx.coroutines.CoroutineDispatcher

object FactsRepositoryFactory {
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
