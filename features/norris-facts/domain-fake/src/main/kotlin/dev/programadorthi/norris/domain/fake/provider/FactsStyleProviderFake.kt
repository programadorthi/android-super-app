package dev.programadorthi.norris.domain.fake.provider

import dev.programadorthi.norris.domain.provider.FactsStyleProvider

class FactsStyleProviderFake : FactsStyleProvider {
    var headlineStyle: Int = -1
    var subtitleStyle: Int = -1

    override fun providerHeadline(): Int = headlineStyle

    override fun providerSubtitle(): Int = subtitleStyle

    override fun provideHeadlineOrSubtitle(predicate: () -> Boolean): Int =
        if (headlineStyle > -1) headlineStyle else subtitleStyle
}
