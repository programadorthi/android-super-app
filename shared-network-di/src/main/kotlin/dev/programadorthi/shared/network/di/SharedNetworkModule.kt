package dev.programadorthi.shared.network.di

import dev.programadorthi.shared.domain.DomainInjectionTags
import dev.programadorthi.shared.network.JsonParser
import dev.programadorthi.shared.network.manager.NetworkManager
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object SharedNetworkModule {
    operator fun invoke() = DI.Module(name = "shared-network") {
        bindProvider {
            NetworkManager(
                connectionCheck = instance(),
                networkingErrorMapper = instance(),
                ioDispatcher = instance(DomainInjectionTags.IO_DISPATCHER)
            )
        }
        bindSingleton { JsonParser }
    }
}
