package dev.programadorthi.shared.network.manager

import dev.programadorthi.shared.network.ConnectionCheck
import dev.programadorthi.shared.network.exception.NetworkingErrorMapper
import dev.programadorthi.shared.network.mapper.RemoteMapper
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
