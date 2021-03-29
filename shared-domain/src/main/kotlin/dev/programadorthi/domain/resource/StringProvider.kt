package dev.programadorthi.domain.resource

interface StringProvider {
    suspend fun getString(stringId: Int): String
    suspend fun getString(stringId: Int, vararg args: Any): String
}