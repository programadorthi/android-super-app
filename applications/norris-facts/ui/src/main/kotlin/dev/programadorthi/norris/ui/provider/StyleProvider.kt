package dev.programadorthi.norris.ui.provider

interface StyleProvider {
    fun providerHeadline(): Int
    fun providerSubtitle(): Int

    companion object Instance {
        operator fun invoke(): StyleProvider = StyleProviderImpl()
    }
}
