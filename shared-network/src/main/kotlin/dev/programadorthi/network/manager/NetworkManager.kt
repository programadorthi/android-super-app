package dev.programadorthi.network.manager

import dev.programadorthi.network.ConnectionCheck
import dev.programadorthi.network.exception.NetworkingErrorMapper
import kotlinx.coroutines.CoroutineDispatcher

interface NetworkManager {
    suspend fun performAndDone(request: suspend () -> Unit)
    suspend fun <Data> performAndReturnsData(request: suspend () -> Data): Data
    suspend fun <From, To> performAndReturnsMappedData(
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
