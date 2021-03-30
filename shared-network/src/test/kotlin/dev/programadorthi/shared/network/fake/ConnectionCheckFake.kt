package dev.programadorthi.shared.network.fake

import dev.programadorthi.shared.network.ConnectionCheck

class ConnectionCheckFake(var hasConnection: Boolean = true) : ConnectionCheck {
    override suspend fun hasInternetConnection(): Boolean = hasConnection
}
