package dev.programadorthi.shared.domain.network

interface ConnectionCheck {
    suspend fun hasInternetConnection(): Boolean
}
