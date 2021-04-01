package dev.programadorthi.shared.network.fake

import dev.programadorthi.shared.domain.network.ConnectionCheck

class ConnectionCheckFake(var hasConnection: Boolean = true) : ConnectionCheck {
    override suspend fun hasInternetConnection(): Boolean = hasConnection
}
