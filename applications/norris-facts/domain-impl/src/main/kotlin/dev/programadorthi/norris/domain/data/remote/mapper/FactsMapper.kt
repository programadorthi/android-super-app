package dev.programadorthi.norris.domain.data.remote.mapper

import dev.programadorthi.norris.domain.data.remote.raw.FactRaw
import dev.programadorthi.norris.domain.data.remote.raw.FactsResponseRaw
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.shared.network.mapper.RemoteMapper

internal object FactsMapper : RemoteMapper<FactsResponseRaw, List<Fact>>() {

    override fun checkEssentialParams(missingFields: MutableSet<String>, raw: FactsResponseRaw) {
        if (raw.result == null) {
            missingFields += FactsResponseRaw.RESULT_FIELD
        }
        val facts = raw.result ?: return
        for (fact in facts) {
            val missingField = when {
                fact.id == null -> FactRaw.ID_FIELD
                fact.url == null -> FactRaw.URL_FIELD
                fact.value == null -> FactRaw.VALUE_FIELD
                else -> continue
            }
            missingFields += missingField
        }
    }

    override fun mapRawToModel(raw: FactsResponseRaw): List<Fact> {
        val items = raw.result ?: return emptyList()
        return items.map {
            Fact(
                id = it.id ?: "",
                url = it.url ?: "",
                value = it.value ?: "",
                categories = it.categories ?: emptyList()
            )
        }
    }
}
