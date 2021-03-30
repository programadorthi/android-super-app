package dev.programadorthi.shared.domain.persist

interface PreferencesManager {
    fun <T> getItem(key: String): T
    fun <T> putItem(key: String, item: T)
}
