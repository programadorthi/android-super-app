package dev.programadorthi.norris.ui.di

import dev.programadorthi.norris.ui.FactsViewModel
import dev.programadorthi.norris.ui.SearchFactsViewModel
import dev.programadorthi.shared.database.di.SharedDatabaseModule
import dev.programadorthi.shared.domain.InjectionTags
import dev.programadorthi.shared.network.di.SharedNetworkModule
import dev.programadorthi.shared.ui.di.SharedUIModule
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
