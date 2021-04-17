package dev.programadorthi.norris.di

import dev.programadorthi.norris.domain.data.FactsRepositoryFactory
import dev.programadorthi.norris.domain.data.local.LocalFactsRepositoryFactory
import dev.programadorthi.norris.domain.data.remote.FactsService
import dev.programadorthi.norris.domain.data.remote.mapper.FactsMapper
import dev.programadorthi.norris.domain.data.remote.repository.RemoteFactsRepositoryFactory
import dev.programadorthi.norris.domain.usecase.FactsUseCaseFactory
import dev.programadorthi.norris.domain.viewmodel.FactsViewModelFactory
import dev.programadorthi.norris.domain.viewmodel.SearchFactsViewModelFactory
import dev.programadorthi.shared.domain.DomainInjectionTags
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import retrofit2.Retrofit

object NorrisModule {
    operator fun invoke() = DI.Module(name = "norris-di") {
        bindSingleton {
            val retrofit = instance<Retrofit>()
            retrofit.create(FactsService::class.java)
        }
        bindProvider {
            LocalFactsRepositoryFactory(
                database = instance()
            )
        }
        bindProvider {
            RemoteFactsRepositoryFactory(
                factsMapper = FactsMapper,
                factsService = instance(),
                networkManager = instance()
            )
        }
        bindProvider {
            FactsRepositoryFactory(
                localFactsRepository = instance(),
                remoteFactsRepository = instance(),
                ioDispatcher = instance(DomainInjectionTags.IO_DISPATCHER)
            )
        }
        bindProvider {
            FactsUseCaseFactory(factsRepository = instance())
        }
        bindProvider {
            FactsViewModelFactory(
                factsUseCase = instance(),
                factsTextProvider = instance(),
                factsStyleProvider = instance(),
                ioDispatcher = instance(DomainInjectionTags.IO_DISPATCHER)
            )
        }
        bindProvider {
            SearchFactsViewModelFactory(
                factsUseCase = instance(),
                sharedTextProvider = instance(),
                ioDispatcher = instance(DomainInjectionTags.IO_DISPATCHER)
            )
        }
    }
}
