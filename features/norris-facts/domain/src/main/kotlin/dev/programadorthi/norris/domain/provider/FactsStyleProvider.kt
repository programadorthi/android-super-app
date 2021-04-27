package dev.programadorthi.norris.domain.provider

interface FactsStyleProvider {
    fun providerHeadline(): Int
    fun providerSubtitle(): Int

    /**
     * Provide a style based on predicate result
     *
     * @return Headline style if predicate returns true. Subtitle style otherwise
     */
    fun provideHeadlineOrSubtitle(predicate: () -> Boolean): Int =
        if (predicate.invoke()) providerHeadline() else providerSubtitle()
}
