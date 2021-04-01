package dev.programadorthi.norris.data

import dev.programadorthi.norris.data.local.LocalFactsRepository
import dev.programadorthi.norris.data.remote.RemoteFactsRepository
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
