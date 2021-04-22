package dev.programadorthi.shared.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.programadorthi.shared.domain.di.qualifier.IODispatcher
import dev.programadorthi.shared.domain.exception.NetworkingErrorMapper
import dev.programadorthi.shared.domain.report.CrashReport
import dev.programadorthi.shared.domain.viewmodel.ViewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Module
@InstallIn(SingletonComponent::class)
object SharedDomainModule {

    @IODispatcher
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideViewModelScope(
        @IODispatcher ioDispatcher: CoroutineDispatcher
    ): ViewModelScope = ViewModelScope(
        coroutineContext = Job() + ioDispatcher
    )

    @Provides
    fun provideNetworkingErrorMapper(
        crashReport: CrashReport
    ): NetworkingErrorMapper = NetworkingErrorMapper(crashReport)
}
