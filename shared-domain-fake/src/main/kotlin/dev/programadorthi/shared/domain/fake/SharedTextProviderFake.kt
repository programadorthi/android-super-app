package dev.programadorthi.shared.domain.fake

import dev.programadorthi.shared.domain.provider.SharedTextProvider

class SharedTextProviderFake : SharedTextProvider {
    var noInternetConnection: String = ""
    var somethingWrong: String = ""

    override fun noInternetConnection(): String = noInternetConnection

    override fun somethingWrong(): String = somethingWrong
}
