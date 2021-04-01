package dev.programadorthi.shared.network.manager

import dev.programadorthi.shared.domain.exception.NetworkingErrorMapper
import dev.programadorthi.shared.domain.network.ConnectionCheck
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

interface NetworkManager {
    suspend fun <T> execute(request: suspend CoroutineScope.() -> T): T

    companion object Instance {
        operator fun invoke(
            connectionCheck: ConnectionCheck,
            networkingErrorMapper: NetworkingErrorMapper,
            ioDispatcher: CoroutineDispatcher
        ): NetworkManager =
            DefaultNetworkManager(
                connectionCheck,
                networkingErrorMapper,
                ioDispatcher
            )
    }
}
