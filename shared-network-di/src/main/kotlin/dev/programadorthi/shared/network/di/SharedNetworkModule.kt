package dev.programadorthi.shared.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import dev.programadorthi.shared.domain.di.qualifier.IODispatcher
import dev.programadorthi.shared.domain.exception.NetworkingErrorMapper
import dev.programadorthi.shared.domain.network.ConnectionCheck
import dev.programadorthi.shared.network.JsonParser
import dev.programadorthi.shared.network.manager.NetworkManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@DisableInstallInCheck
@Module
object SharedNetworkModule {
    @Singleton
    @Provides
    fun provideJsonParser(): Json = JsonParser

    @Provides
    fun provideNetworkManager(
        connectionCheck: ConnectionCheck,
        networkingErrorMapper: NetworkingErrorMapper,
        @IODispatcher ioDispatcher: CoroutineDispatcher
    ): NetworkManager = NetworkManager(
        connectionCheck = connectionCheck,
        networkingErrorMapper = networkingErrorMapper,
        ioDispatcher = ioDispatcher
    )
}
