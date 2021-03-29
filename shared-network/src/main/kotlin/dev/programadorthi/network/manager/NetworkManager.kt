package dev.programadorthi.network.manager

import dev.programadorthi.network.ConnectionCheck
import dev.programadorthi.network.exception.NetworkingErrorMapper
import kotlinx.coroutines.CoroutineDispatcher

interface NetworkManager {
    suspend fun send(request: suspend () -> Unit)
    suspend fun <Data> sendAndGet(request: suspend () -> Data): Data
    suspend fun <From, To> sendAndGetMapped(
        mapper: (From) -> To,
        request: suspend () -> From
    ): To

    companion object Instance {
        fun create(
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
