package dev.programadorthi.norris.domain.fake.provider

import dev.programadorthi.norris.domain.provider.FactsTextProvider

class FactsTextProviderFake : FactsTextProvider {
    var emptySearchTermText: String = ""
    var generalFailureText: String = ""
    var withoutCategoryText: String = ""

    override fun emptySearchTerm(): String = emptySearchTermText

    override fun generalFailure(): String = generalFailureText

    override fun withoutCategory(): String = withoutCategoryText
}
