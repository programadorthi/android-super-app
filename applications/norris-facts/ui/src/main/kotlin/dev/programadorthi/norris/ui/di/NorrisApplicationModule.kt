package dev.programadorthi.norris.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.programadorthi.norris.ui.BuildConfig
import dev.programadorthi.shared.database.android.SharedDatabaseAndroidModule
import dev.programadorthi.shared.database.di.SharedDatabaseModule
import dev.programadorthi.shared.domain.di.SharedDomainModule
import dev.programadorthi.shared.network.di.SharedNetworkModule
import dev.programadorthi.shared.retrofit.di.SharedRetrofitModule
import dev.programadorthi.shared.retrofit.di.qualifier.BaseUrl
import dev.programadorthi.shared.ui.di.SharedUIModule

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        SharedDatabaseModule::class,
        SharedDatabaseAndroidModule::class,
        SharedDomainModule::class,
        SharedRetrofitModule::class,
        SharedNetworkModule::class,
        SharedUIModule::class
    ]
)
object NorrisApplicationModule {
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}
