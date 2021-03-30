package dev.programadorthi.shared.network

interface ConnectionCheck {
    suspend fun hasInternetConnection(): Boolean
}
