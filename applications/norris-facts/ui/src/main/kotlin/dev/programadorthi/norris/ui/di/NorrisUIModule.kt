package dev.programadorthi.norris.ui.di

import dev.programadorthi.norris.ui.viewmodel.FactsViewModel
import dev.programadorthi.norris.ui.viewmodel.SearchFactsViewModel
import dev.programadorthi.shared.domain.InjectionTags
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object NorrisUIModule {
    operator fun invoke() = DI.Module(name = "norris-ui") {
        bindProvider {
            FactsViewModel(
                factsUseCase = instance(),
                stringProvider = instance(),
                ioDispatcher = instance(InjectionTags.IO_DISPATCHER)
            )
        }
        bindProvider {
            SearchFactsViewModel(
                factsUseCase = instance(),
                stringProvider = instance(),
                ioDispatcher = instance(InjectionTags.IO_DISPATCHER)
            )
        }
    }
}
