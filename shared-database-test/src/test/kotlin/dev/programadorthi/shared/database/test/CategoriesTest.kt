package dev.programadorthi.shared.database.test

import dev.programadorthi.shared.database.Categories
import dev.programadorthi.shared.database.fake.SuperAppFake
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class CategoriesTest {
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
    fun `should database has no categories`() {
        val expected = emptyList<String>()
        val queries = superAppFake.database.norrisQueries
        val categories = queries.selectCategories().executeAsList()
        assertThat(categories).isEqualTo(expected)
    }

    @Test
    fun `should persist a category`() {
        val expected = "cat1"
        val queries = superAppFake.database.norrisQueries
        queries.insertCategory(Categories(name = expected))
        val categories = queries.selectCategories().executeAsOne()
        assertThat(categories).isEqualTo(expected)
    }

    @Test
    fun `should persist many categories`() {
        val expected = listOf("cat1", "cat2", "cat3", "cat4", "cat5")
        val queries = superAppFake.database.norrisQueries
        for (cat in expected) {
            queries.insertCategory(Categories(name = cat))
        }
        val categories = queries.selectCategories().executeAsList()
        assertThat(categories).isEqualTo(expected)
    }
}
