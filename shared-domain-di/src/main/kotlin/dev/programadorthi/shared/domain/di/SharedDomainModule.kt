package dev.programadorthi.shared.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import dev.programadorthi.shared.domain.di.qualifier.IODispatcher
import dev.programadorthi.shared.domain.exception.NetworkingErrorMapper
import dev.programadorthi.shared.domain.report.CrashReport
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@DisableInstallInCheck
@Module
object SharedDomainModule {

    @IODispatcher
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideNetworkingErrorMapper(
        crashReport: CrashReport
    ): NetworkingErrorMapper = NetworkingErrorMapper(crashReport)
}
