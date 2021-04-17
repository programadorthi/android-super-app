package dev.programadorthi.norris.di

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import dev.programadorthi.norris.domain.data.FactsRepositoryFactory
import dev.programadorthi.norris.domain.data.LocalFactsRepository
import dev.programadorthi.norris.domain.data.RemoteFactsRepository
import dev.programadorthi.norris.domain.data.local.LocalFactsRepositoryFactory
import dev.programadorthi.norris.domain.data.remote.FactsService
import dev.programadorthi.norris.domain.data.remote.mapper.FactsMapper
import dev.programadorthi.norris.domain.data.remote.repository.RemoteFactsRepositoryFactory
import dev.programadorthi.norris.domain.provider.FactsStyleProvider
import dev.programadorthi.norris.domain.provider.FactsTextProvider
import dev.programadorthi.norris.domain.repository.FactsRepository
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.norris.domain.usecase.FactsUseCaseFactory
import dev.programadorthi.norris.domain.viewmodel.FactsViewModel
import dev.programadorthi.norris.domain.viewmodel.FactsViewModelFactory
import dev.programadorthi.norris.domain.viewmodel.SearchFactsViewModel
import dev.programadorthi.norris.domain.viewmodel.SearchFactsViewModelFactory
import dev.programadorthi.shared.database.NorrisQueries
import dev.programadorthi.shared.domain.di.qualifier.IODispatcher
import dev.programadorthi.shared.domain.provider.SharedTextProvider
import dev.programadorthi.shared.network.manager.NetworkManager
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@DisableInstallInCheck
@Module
object NorrisModule {
    @Provides
    fun provideFactsService(
        retrofit: Retrofit
    ): FactsService = retrofit.create(FactsService::class.java)

    @Provides
    fun provideLocalFactsRepository(
        norrisQueries: NorrisQueries
    ): LocalFactsRepository = LocalFactsRepositoryFactory(norrisQueries)

    @Provides
    fun provideRemoteFactsRepository(
        factsService: FactsService,
        networkManager: NetworkManager
    ): RemoteFactsRepository = RemoteFactsRepositoryFactory(
        factsMapper = FactsMapper,
        factsService = factsService,
        networkManager = networkManager
    )

    @Provides
    fun provideFactsRepository(
        localFactsRepository: LocalFactsRepository,
        remoteFactsRepository: RemoteFactsRepository,
        @IODispatcher ioDispatcher: CoroutineDispatcher
    ): FactsRepository = FactsRepositoryFactory(
        localFactsRepository = localFactsRepository,
        remoteFactsRepository = remoteFactsRepository,
        ioDispatcher = ioDispatcher
    )

    @Provides
    fun provideFactsUseCase(
        factsRepository: FactsRepository
    ): FactsUseCase = FactsUseCaseFactory(factsRepository)

    @Provides
    fun provideFactsViewModel(
        factsUseCase: FactsUseCase,
        factsTextProvider: FactsTextProvider,
        factsStyleProvider: FactsStyleProvider,
        @IODispatcher ioDispatcher: CoroutineDispatcher
    ): FactsViewModel = FactsViewModelFactory(
        factsUseCase = factsUseCase,
        factsTextProvider = factsTextProvider,
        factsStyleProvider = factsStyleProvider,
        ioDispatcher = ioDispatcher
    )

    @Provides
    fun provideSearchFactsViewModel(
        factsUseCase: FactsUseCase,
        sharedTextProvider: SharedTextProvider,
        @IODispatcher ioDispatcher: CoroutineDispatcher
    ): SearchFactsViewModel = SearchFactsViewModelFactory(
        factsUseCase = factsUseCase,
        sharedTextProvider = sharedTextProvider,
        ioDispatcher = ioDispatcher
    )
}
