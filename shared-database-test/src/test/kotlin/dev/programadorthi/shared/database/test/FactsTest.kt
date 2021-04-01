package dev.programadorthi.shared.database.test

import dev.programadorthi.shared.database.Facts
import dev.programadorthi.shared.database.fake.SuperAppFake
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class FactsTest {
    private val superAppFake = SuperAppFake()

    @Before
    fun `before each test`() {
        superAppFake.open()
    }

    @After
    fun `after each test`() {
        superAppFake.close()
    }

    @Test
    fun `should database has no facts`() {
        val expected = emptyList<Facts>()
        val queries = superAppFake.database.norrisQueries
        val categories = queries.selectFacts().executeAsList()
        assertThat(categories).isEqualTo(expected)
    }

    @Test
    fun `should persist a fact`() {
        val expected = Facts(
            id = "id",
            url = "url",
            text = "text"
        )
        val queries = superAppFake.database.norrisQueries
        queries.insertFacts(expected)
        val categories = queries.selectFacts().executeAsOne()
        assertThat(categories).isEqualTo(expected)
    }

    @Test
    fun `should persist many facts`() {
        val expected = List(5) { index ->
            Facts(
                id = "id$index",
                url = "url$index",
                text = "text$index"
            )
        }
        val queries = superAppFake.database.norrisQueries
        for (fact in expected) {
            queries.insertFacts(fact)
        }
        val categories = queries.selectFacts().executeAsList()
        assertThat(categories).isEqualTo(expected)
    }
}
