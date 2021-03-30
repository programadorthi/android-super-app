package dev.programadorthi.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

object RetrofitBuilder {
    private val contentType = "application/json".toMediaType()

    @OptIn(ExperimentalSerializationApi::class)
    private val jsonFactory = JsonParser.asConverterFactory(contentType)

    operator fun invoke(
        baseUrl: String,
        httpClient: OkHttpClient,
        convertFactory: Converter.Factory = jsonFactory
    ): Retrofit = with(Retrofit.Builder()) {
        baseUrl(baseUrl)
        client(httpClient)
        addConverterFactory(convertFactory)
        build()
    }
}
