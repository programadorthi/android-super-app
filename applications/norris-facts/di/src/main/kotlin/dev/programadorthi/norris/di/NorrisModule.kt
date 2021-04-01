package dev.programadorthi.norris.di

import dev.programadorthi.norris.domain.data.FactsRepositoryFactory
import dev.programadorthi.norris.domain.data.local.LocalFactsRepositoryFactory
import dev.programadorthi.norris.domain.data.remote.FactsService
import dev.programadorthi.norris.domain.data.remote.mapper.FactsMapper
import dev.programadorthi.norris.domain.data.remote.repository.RemoteFactsRepositoryFactory
import dev.programadorthi.norris.domain.usecase.FactsUseCaseFactory
import dev.programadorthi.shared.database.SuperApp
import dev.programadorthi.shared.database.di.SharedDatabaseModule
import dev.programadorthi.shared.domain.InjectionTags
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import retrofit2.Retrofit

object NorrisModule {
    operator fun invoke() = DI.Module(name = "norris") {
        import(SharedDatabaseModule())

        bindSingleton {
            val retrofit = instance<Retrofit>()
            retrofit.create(FactsService::class.java)
        }
        bindProvider {
            LocalFactsRepositoryFactory(
                database = instance<SuperApp>().norrisQueries
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
                ioDispatcher = instance(InjectionTags.IO_DISPATCHER)
            )
        }
        bindProvider { FactsUseCaseFactory(factsRepository = instance()) }
    }
}
