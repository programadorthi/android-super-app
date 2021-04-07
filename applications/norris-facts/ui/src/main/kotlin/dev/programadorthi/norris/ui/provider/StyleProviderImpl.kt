package dev.programadorthi.norris.ui.provider

import com.google.android.material.R

internal class StyleProviderImpl : StyleProvider {
    override fun providerHeadline(): Int =
        R.style.TextAppearance_MaterialComponents_Headline4

    override fun providerSubtitle(): Int =
        R.style.TextAppearance_MaterialComponents_Subtitle1
}
