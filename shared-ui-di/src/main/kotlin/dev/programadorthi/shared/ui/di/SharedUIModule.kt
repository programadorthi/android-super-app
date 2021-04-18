package dev.programadorthi.shared.ui.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.programadorthi.shared.domain.network.ConnectionCheck
import dev.programadorthi.shared.domain.provider.SharedTextProvider
import dev.programadorthi.shared.domain.report.CrashReport
import dev.programadorthi.shared.ui.network.ConnectionCheckFactory
import dev.programadorthi.shared.ui.provider.SharedTextProviderFactory
import dev.programadorthi.shared.ui.report.CrashReportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedUIModule {
    @Singleton
    @Provides
    fun provideConnectionCheck(
        @ApplicationContext context: Context
    ): ConnectionCheck = ConnectionCheckFactory(context)

    @Singleton
    @Provides
    fun provideCrashReport(): CrashReport = CrashReportFactory()

    @Provides
    fun provideSharedTextProvider(
        @ApplicationContext context: Context
    ): SharedTextProvider = SharedTextProviderFactory(context)
}
