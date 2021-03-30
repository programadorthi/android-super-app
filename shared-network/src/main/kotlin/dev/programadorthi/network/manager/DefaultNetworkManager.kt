package dev.programadorthi.network.manager

import dev.programadorthi.network.ConnectionCheck
import dev.programadorthi.network.exception.NetworkingError
import dev.programadorthi.network.exception.NetworkingErrorMapper
import dev.programadorthi.network.mapper.RemoteMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DefaultNetworkManager(
    private val connectionCheck: ConnectionCheck,
    private val networkingErrorMapper: NetworkingErrorMapper,
    private val ioDispatcher: CoroutineDispatcher
) : NetworkManager {
    override suspend fun send(request: suspend () -> Unit) {
        withContext(ioDispatcher) {
            checkConnection()
            try {
                request.invoke()
            } catch (ex: Throwable) {
                throw networkingErrorMapper.mapper(ex)
            }
        }
    }

    override suspend fun <RawData> sendAndGet(request: suspend () -> RawData): RawData =
        withContext(ioDispatcher) {
            checkConnection()
            try {
                request.invoke()
            } catch (ex: Throwable) {
                throw networkingErrorMapper.mapper(ex)
            }
        }

    override suspend fun <Raw, Model> sendAndGetMapped(
        mapper: RemoteMapper<Raw, Model>,
        request: suspend () -> Raw
    ): Model = withContext(ioDispatcher) {
        checkConnection()
        try {
            mapper.invoke(request.invoke())
        } catch (ex: Throwable) {
            throw networkingErrorMapper.mapper(ex)
        }
    }

    private suspend fun checkConnection() {
        if (connectionCheck.hasInternetConnection().not()) {
            throw NetworkingError.NoInternetConnection
        }
    }
}
