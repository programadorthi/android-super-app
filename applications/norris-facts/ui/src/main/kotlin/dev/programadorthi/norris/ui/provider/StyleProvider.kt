package dev.programadorthi.norris.ui.provider

interface StyleProvider {
    fun providerHeadline(): Int
    fun providerSubtitle(): Int

    /**
     * Provide a style based on predicate result
     *
     * @return Headline style if predicate returns true. Subtitle style otherwise
     */
    fun provideHeadlineOrSubtitle(predicate: () -> Boolean): Int =
        if (predicate.invoke()) providerSubtitle() else providerHeadline()

    companion object Instance {
        operator fun invoke(): StyleProvider = StyleProviderImpl()
    }
}
