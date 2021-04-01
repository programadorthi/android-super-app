package dev.programadorthi.shared.network.fake

import dev.programadorthi.shared.network.manager.NetworkManager
import kotlinx.coroutines.CoroutineScope

class NetworkManagerFake(
    private val coroutineScope: CoroutineScope
) : NetworkManager {
    override suspend fun <T> execute(
        request: suspend CoroutineScope.() -> T
    ): T = request.invoke(coroutineScope)
}
