package dev.programadorthi.domain.test

import dev.programadorthi.norris.domain.data.remote.mapper.FactsMapper
import dev.programadorthi.norris.domain.data.remote.raw.FactRaw
import dev.programadorthi.norris.domain.data.remote.raw.FactsResponseRaw
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.shared.domain.exception.NetworkingError
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class FactsMapperTest {

    private val mapper = FactsMapper
    private val missingFieldsList = listOf(
        FactRaw.ID_FIELD to FactRaw(
            url = "url",
            value = "value",
            categories = emptyList(),
        ),
        FactRaw.URL_FIELD to FactRaw(
            id = "id",
            value = "value",
            categories = emptyList(),
        ),
        FactRaw.VALUE_FIELD to FactRaw(
            id = "id",
            url = "url",
            categories = emptyList(),
        )
    )

    @Test
    fun `should have missing result field`() {
        val missingFields = setOf(FactsResponseRaw.RESULT_FIELD)
        val raw = FactsResponseRaw()
        val expected = raw.toExpectation(missingFields)
        assertThatThrownBy {
            mapper.invoke(raw)
        }.isInstanceOf(expected::class.java)
            .hasMessage(expected.message)
    }

    @Test
    fun `should have missing any fact field`() {
        val (field, factRaw) = missingFieldsList.shuffled().first()
        val missingFields = setOf(field)
        val raw = FactsResponseRaw(
            result = listOf(factRaw)
        )
        val expected = raw.toExpectation(missingFields)
        assertThatThrownBy {
            mapper.invoke(raw)
        }.isInstanceOf(expected::class.java)
            .hasMessage(expected.message)
    }

    @Test
    fun `should map raw to model successfully`() {
        val factRaw = FactRaw(
            id = "id",
            url = "url",
            value = "value",
            categories = emptyList(),
        )
        val expected = listOf(
            Fact(
                id = factRaw.id ?: "",
                url = factRaw.url ?: "",
                value = factRaw.value ?: "",
                categories = factRaw.categories ?: emptyList()
            )
        )
        val raw = FactsResponseRaw(result = listOf(factRaw))
        val result = mapper.invoke(raw)
        assertThat(result).isEqualTo(expected)
    }

    private fun FactsResponseRaw.toExpectation(
        missingFields: Set<String>
    ): NetworkingError.EssentialParamMissing {
        val params = missingFields.joinToString(prefix = "[", postfix = "]")
        return NetworkingError.EssentialParamMissing(missingParams = params, rawObject = this)
    }
}
