package dev.programadorthi.norris.ui.di

import androidx.lifecycle.ViewModelProvider
import dev.programadorthi.norris.ui.FactsViewModel
import dev.programadorthi.norris.ui.SearchFactsViewModel
import dev.programadorthi.norris.ui.connection.ConnectionCheckImpl
import dev.programadorthi.norris.ui.factory.ViewModelFactory
import dev.programadorthi.shared.domain.InjectionTags
import dev.programadorthi.shared.domain.network.ConnectionCheck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object NorrisUIModule {
    operator fun invoke() = DI.Module(name = "norris-ui") {
        // TODO: migrate to a global module
        bindProvider(InjectionTags.IO_SCOPE) { CoroutineScope(Dispatchers.IO) }
        bindProvider<ViewModelProvider.Factory> { ViewModelFactory(di) }
        bindSingleton<ConnectionCheck> { ConnectionCheckImpl(context = instance()) }

        bindProvider {
            FactsViewModel(
                factsUseCase = instance(),
                stringProvider = instance(),
                ioScope = instance(InjectionTags.IO_SCOPE)
            )
        }
        bindProvider {
            SearchFactsViewModel(
                factsUseCase = instance(),
                stringProvider = instance(),
                ioScope = instance(InjectionTags.IO_SCOPE)
            )
        }
    }
}
