package dev.programadorthi.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

object RetrofitBuilder {
    private val contentType = "application/json".toMediaType()
    @OptIn(ExperimentalSerializationApi::class)
    private val jsonFactory = Json {
        allowSpecialFloatingPointValues = true
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
        useArrayPolymorphism = true
    }.asConverterFactory(contentType)

    operator fun invoke(
        endpoint: String,
        httpClient: OkHttpClient,
        convertFactory: Converter.Factory = jsonFactory
    ): Retrofit = with(Retrofit.Builder()) {
        baseUrl(endpoint)
        client(httpClient)
        addConverterFactory(convertFactory)
        build()
    }
}
