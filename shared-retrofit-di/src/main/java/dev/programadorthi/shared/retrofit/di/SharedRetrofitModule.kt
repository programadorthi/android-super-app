package dev.programadorthi.shared.retrofit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.programadorthi.shared.retrofit.RetrofitBuilder
import dev.programadorthi.shared.retrofit.di.qualifier.BaseUrl
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedRetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        json: Json
    ): Retrofit = RetrofitBuilder(
        baseUrl = baseUrl,
        json = json
    )
}
