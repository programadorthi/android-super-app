package dev.programadorthi.norris.ui.di

import dev.programadorthi.norris.domain.provider.FactsStyleProvider
import dev.programadorthi.norris.domain.provider.FactsTextProvider
import dev.programadorthi.norris.ui.provider.FactsStyleProviderImpl
import dev.programadorthi.norris.ui.provider.FactsTextProviderImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object NorrisUIModule {
    operator fun invoke() = DI.Module(name = "norris-ui") {
        bindProvider<FactsStyleProvider> { FactsStyleProviderImpl() }
        bindProvider<FactsTextProvider> {
            FactsTextProviderImpl(
                context = instance(),
                sharedTextProvider = instance()
            )
        }
    }
}
