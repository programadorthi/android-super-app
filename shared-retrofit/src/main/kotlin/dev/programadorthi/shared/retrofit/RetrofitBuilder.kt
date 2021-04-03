package dev.programadorthi.shared.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitBuilder {
    private val contentType = "application/json".toMediaType()

    operator fun invoke(
        baseUrl: String,
        json: Json,
        httpClient: OkHttpClient = OkHttpClient()
    ): Retrofit = with(Retrofit.Builder()) {
        baseUrl(baseUrl)
        client(httpClient)
        addConverterFactory(json.asConverterFactory(contentType))
        build()
    }
}
