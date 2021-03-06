package dev.programadorthi.norris.domain.data.remote.raw

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FactRaw(
    @SerialName(ID_FIELD)
    val id: String? = null,
    @SerialName(URL_FIELD)
    val url: String? = null,
    @SerialName(VALUE_FIELD)
    val value: String? = null,
    @SerialName("categories")
    val categories: List<String>? = null
) {
    companion object {
        const val ID_FIELD = "id"
        const val URL_FIELD = "url"
        const val VALUE_FIELD = "value"
    }
}
