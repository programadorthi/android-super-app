package dev.programadorthi.shared.database.test

import dev.programadorthi.shared.database.LastSearch
import dev.programadorthi.shared.database.fake.SuperAppFake
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class LastSearchTest {
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
    fun `should database has no last search`() {
        val expected = emptyList<String>()
        val queries = superAppFake.database.norrisQueries
        val categories = queries.selectLastSearches().executeAsList()
        assertThat(categories).isEqualTo(expected)
    }

    @Test
    fun `should persist the last search`() {
        val expected = listOf("animal")
        val queries = superAppFake.database.norrisQueries
        for (term in expected) {
            queries.insertLastSearch(LastSearch(term = term))
        }
        val categories = queries.selectLastSearches().executeAsList()
        assertThat(categories).isEqualTo(expected)
    }
}
