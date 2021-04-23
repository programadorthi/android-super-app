package dev.programadorthi.norris.ui.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.multibindings.IntoMap
import dev.programadorthi.norris.di.NorrisModule
import dev.programadorthi.norris.domain.provider.FactsStyleProvider
import dev.programadorthi.norris.domain.provider.FactsTextProvider
import dev.programadorthi.norris.domain.viewmodel.FactsViewModel
import dev.programadorthi.norris.domain.viewmodel.SearchFactsViewModel
import dev.programadorthi.norris.ui.provider.FactsStyleProviderImpl
import dev.programadorthi.norris.ui.provider.FactsTextProviderImpl
import dev.programadorthi.shared.domain.di.key.ViewModelKey
import dev.programadorthi.shared.domain.provider.SharedTextProvider
import dev.programadorthi.shared.domain.viewmodel.ViewModel

@InstallIn(ActivityComponent::class)
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

@Module
@InstallIn(ActivityComponent::class)
abstract class NorrisProvidersModule {
    @Binds
    @IntoMap
    @ViewModelKey(FactsViewModel::class)
    abstract fun bindFactsViewModel(factsViewModel: FactsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchFactsViewModel::class)
    abstract fun bindSearchFactsViewModel(searchFactsViewModel: SearchFactsViewModel): ViewModel
}
