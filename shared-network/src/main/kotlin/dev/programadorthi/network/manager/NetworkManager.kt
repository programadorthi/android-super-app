package dev.programadorthi.network.manager

import dev.programadorthi.network.ConnectionCheck
import dev.programadorthi.network.exception.NetworkingErrorMapper
import dev.programadorthi.network.mapper.RemoteMapper
import kotlinx.coroutines.CoroutineDispatcher

interface NetworkManager {
    suspend fun send(request: suspend () -> Unit)
    suspend fun <RawData> sendAndGet(request: suspend () -> RawData): RawData
    suspend fun <Raw, Model> sendAndGetMapped(
        mapper: RemoteMapper<Raw, Model>,
        request: suspend () -> Raw
    ): Model

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
