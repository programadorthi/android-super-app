package dev.programadorthi.norris.di

import com.squareup.sqldelight.db.SqlDriver
import dev.programadorthi.norris.Norris
import dev.programadorthi.norris.data.FactsRepositoryFactory
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.norris.local.LocalFactsRepositoryFactory
import dev.programadorthi.norris.remote.FactsService
import dev.programadorthi.norris.remote.repository.RemoteFactsRepositoryFactory
import dev.programadorthi.shared.domain.InjectionTags
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import retrofit2.Retrofit

object NorrisModule {
    operator fun invoke() = DI.Module(name = "norris") {
        bindSingleton {
            val driver = instance<SqlDriver>()
            Norris(driver)
        }
        bindSingleton {
            val retrofit = instance<Retrofit>()
            retrofit.create(FactsService::class.java)
        }
        bindProvider {
            LocalFactsRepositoryFactory(
                database = instance<Norris>().norrisQueries
            )
        }
        bindProvider {
            RemoteFactsRepositoryFactory(
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
        bindProvider { FactsUseCase(factsRepository = instance()) }
    }
}
