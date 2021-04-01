package dev.programadorthi.domain.test

import dev.programadorthi.norris.domain.data.LocalFactsRepository
import dev.programadorthi.norris.domain.data.local.LocalFactsRepositoryFactory
import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch
import dev.programadorthi.shared.database.fake.SuperAppFake
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class LocalFactsRepositoryTest {
    private val fakeSuperApp = SuperAppFake()

    private lateinit var repository: LocalFactsRepository

    @Before
    fun `before each test`() {
        fakeSuperApp.open()
        repository = LocalFactsRepositoryFactory(
            database = fakeSuperApp.database.norrisQueries
        )
    }

    @After
    fun `after each test`() {
        fakeSuperApp.close()
    }

    @Test
    fun `should have no categories when database is created`() = runBlockingTest {
        val expected = emptyList<String>()
        val result = repository.getCategories()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should have no facts when database is created`() = runBlockingTest {
        val expected = emptyList<Fact>()
        val result = repository.getFacts()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should have no last searches when database is created`() = runBlockingTest {
        val expected = emptyList<String>()
        val result = repository.getLastSearches()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should persist categories when ask to save any`() = runBlockingTest {
        val expected = List(5) { index -> "cat$index" }
        val categories = expected.map { cat -> Category(name = cat) }
        repository.saveCategories(categories)
        val result = repository.getCategories()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should persist facts when ask to save any`() = runBlockingTest {
        val expected = List(5) { index ->
            Fact(
                id = "id$index",
                url = "url$index",
                value = "value$index",
                categories = emptyList()
            )
        }
        repository.saveFacts(expected)
        val result = repository.getFacts()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should persist last search when ask to save any`() = runBlockingTest {
        val expected = listOf("animal")
        repository.saveNewSearch(LastSearch(term = expected.first()))
        val result = repository.getLastSearches()
        assertThat(result).isEqualTo(expected)
    }
}
