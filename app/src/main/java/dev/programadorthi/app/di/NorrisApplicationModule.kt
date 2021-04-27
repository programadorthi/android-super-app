package dev.programadorthi.norris.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.programadorthi.norris.ui.BuildConfig
import dev.programadorthi.shared.retrofit.di.qualifier.BaseUrl

@Module
@InstallIn(SingletonComponent::class)
object NorrisApplicationModule {
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}
