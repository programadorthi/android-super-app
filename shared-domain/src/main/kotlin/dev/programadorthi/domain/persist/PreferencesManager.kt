package dev.programadorthi.domain.persist

interface PreferencesManager {
    fun getItem(key: String): String
    fun putItem(key: String, item: String)
}
