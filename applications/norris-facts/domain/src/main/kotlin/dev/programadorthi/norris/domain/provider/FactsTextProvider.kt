package dev.programadorthi.norris.domain.provider

interface FactsTextProvider {
    fun emptySearchTerm(): String
    fun generalFailure(): String
    fun withoutCategory(): String
}
