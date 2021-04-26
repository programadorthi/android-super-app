package dev.programadorthi.norris.domain.data.remote.raw

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FactsResponseRaw(
    @SerialName(RESULT_FIELD)
    val result: List<FactRaw>? = null
) {
    companion object {
        const val RESULT_FIELD = "result"
    }
}
