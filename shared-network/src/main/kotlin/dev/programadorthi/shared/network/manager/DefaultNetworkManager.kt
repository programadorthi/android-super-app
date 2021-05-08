package dev.programadorthi.shared.network.manager

import dev.programadorthi.shared.domain.exception.NetworkingError
import dev.programadorthi.shared.domain.exception.NetworkingErrorMapper
import dev.programadorthi.shared.domain.network.ConnectionCheck
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import java.io.IOException

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
        } catch (ex: SerializationException) {
            throw networkingErrorMapper.mapper(ex)
        } catch (ex: IOException) {
            throw networkingErrorMapper.mapper(ex)
        } catch (ex: Throwable) {
            throw networkingErrorMapper.mapper(ex)
        }
    }
}
