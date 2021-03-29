package dev.programadorthi.domain.resource

interface StringProvider {
    fun getString(stringId: Int): String
    fun getString(stringId: Int, vararg args: Any): String
}
