package dev.programadorthi.norris.ui.fake.provider

import dev.programadorthi.norris.ui.provider.StyleProvider

class StyleProviderFake : StyleProvider {
    var headline = -1
    var subtitle = -1

    override fun providerHeadline(): Int = headline

    override fun providerSubtitle(): Int = subtitle

    override fun provideHeadlineOrSubtitle(predicate: () -> Boolean): Int =
        if (headline > -1) headline else subtitle
}
