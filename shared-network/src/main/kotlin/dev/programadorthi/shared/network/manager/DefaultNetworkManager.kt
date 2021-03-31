package dev.programadorthi.shared.network.manager

import dev.programadorthi.shared.network.ConnectionCheck
import dev.programadorthi.shared.network.exception.NetworkingError
import dev.programadorthi.shared.network.exception.NetworkingErrorMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

internal class DefaultNetworkManager(
    private val connectionCheck: ConnectionCheck,
    private val networkingErrorMapper: NetworkingErrorMapper,
    private val ioDispatcher: CoroutineDispatcher
) : NetworkManager {
    override suspend fun <T> execute(
        request: suspend CoroutineScope.() -> T
    ): T = withContext(ioDispatcher) {
        if (connectionCheck.hasInternetConnection().not()) {
            throw NetworkingError.NoInternetConnection
        }
        try {
            request.invoke(this)
        } catch (ex: Throwable) {
            throw networkingErrorMapper.mapper(ex)
        }
    }
}
