package dev.programadorthi.network

import kotlinx.serialization.json.Json

val JsonParser = Json {
    allowSpecialFloatingPointValues = true
    encodeDefaults = true
    ignoreUnknownKeys = true
    isLenient = true
    useArrayPolymorphism = true
}
