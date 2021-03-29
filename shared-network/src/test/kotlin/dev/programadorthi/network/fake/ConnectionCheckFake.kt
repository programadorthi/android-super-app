package dev.programadorthi.network.fake

import dev.programadorthi.network.ConnectionCheck

class ConnectionCheckFake(var hasConnection: Boolean = true) : ConnectionCheck {
    override suspend fun hasInternetConnection(): Boolean = hasConnection
}
