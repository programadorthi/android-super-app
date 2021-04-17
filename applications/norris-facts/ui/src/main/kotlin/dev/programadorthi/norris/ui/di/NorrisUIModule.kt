package dev.programadorthi.norris.ui.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.programadorthi.norris.di.NorrisModule
import dev.programadorthi.norris.domain.provider.FactsStyleProvider
import dev.programadorthi.norris.domain.provider.FactsTextProvider
import dev.programadorthi.norris.ui.provider.FactsStyleProviderImpl
import dev.programadorthi.norris.ui.provider.FactsTextProviderImpl
import dev.programadorthi.shared.domain.provider.SharedTextProvider

@InstallIn(ViewModelComponent::class)
@Module(
    includes = [
        NorrisModule::class
    ]
)
object NorrisUIModule {
    @Provides
    fun provideFactsStyleProvider(): FactsStyleProvider = FactsStyleProviderImpl()

    @Provides
    fun provideFactsTextProvider(
        @ApplicationContext context: Context,
        sharedTextProvider: SharedTextProvider
    ): FactsTextProvider = FactsTextProviderImpl(
        context = context,
        sharedTextProvider = sharedTextProvider
    )
}
