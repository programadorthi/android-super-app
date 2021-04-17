package dev.programadorthi.shared.domain.provider

interface SharedTextProvider {
    fun noInternetConnection(): String
    fun somethingWrong(): String
}
