package dev.programadorthi.network

interface ConnectionCheck {
    suspend fun hasInternetConnection(): Boolean
}
