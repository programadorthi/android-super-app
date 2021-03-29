package dev.programadorthi.domain.persist

interface PreferencesManager {
    suspend fun getItem(key: String): String
    suspend fun putItem(key: String, item: String)
}
