package dev.programadorthi.domain.test

import dev.programadorthi.norris.domain.data.FactsRepositoryFactory
import dev.programadorthi.norris.domain.fake.data.LocalFactsRepositoryFake
import dev.programadorthi.norris.domain.fake.data.RemoteFactsRepositoryFake
import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch
import dev.programadorthi.norris.domain.repository.FactsRepository
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class FactsRepositoryTest {

    private val random = Random.Default
    private val dispatcher = TestCoroutineDispatcher()
    private val localFactsRepository = LocalFactsRepositoryFake()
    private val remoteFactsRepository = RemoteFactsRepositoryFake()
    private lateinit var factsRepository: FactsRepository

    @Before
    fun `before each test`() {
        factsRepository = FactsRepositoryFactory(
            localFactsRepository = localFactsRepository,
            remoteFactsRepository = remoteFactsRepository,
            ioDispatcher = dispatcher
        )
    }

    @After
    fun `after each test`() {
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should get empty categories when limit is less than or equals to zero`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = emptyList<Category>()
            // When
            val result = factsRepository.fetchCategories(limit = 0, shuffle = false)
            val compare = result.getOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get empty categories when there is no local and remote categories`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = emptyList<Category>()
            // When
            val result = factsRepository.fetchCategories(limit = 50, shuffle = false)
            val compare = result.getOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get a failure when fetching local throw an exception`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = Exception("exception thrown")
            localFactsRepository.exceptionToThrow = expected
            // When
            val result = factsRepository.fetchCategories(limit = 50, shuffle = false)
            val compare = result.exceptionOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get a failure when fetching remote throw an exception`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = Exception("exception thrown")
            remoteFactsRepository.exceptionToThrow = expected
            // When
            val result = factsRepository.fetchCategories(limit = 50, shuffle = false)
            val compare = result.exceptionOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get categories from local when local has categories only`() =
        runBlockingTest(dispatcher) {
            // Given
            val listSize = 5
            val remoteCategory = Category(name = "remote")
            val localCategories = List(listSize) { Category(name = "cat$it") }
            localFactsRepository.saveCategories(localCategories)
            remoteFactsRepository.addCategory(remoteCategory.name)
            val limit = random.nextInt(from = 1, until = listSize + 1) // until is exclusive
            val expected = localCategories.take(limit)
            // When
            val result = factsRepository.fetchCategories(limit = limit, shuffle = false)
            val compare = result.getOrNull()
            // Then
            assertThat(compare)
                .isEqualTo(expected)
                .doesNotContain(remoteCategory)
        }

    @Test
    fun `should get categories from remote when local has no categories`() =
        runBlockingTest(dispatcher) {
            // Given
            val listSize = 5
            val remoteCategories = List(listSize) { Category(name = "cat$it") }
            val categories = remoteCategories.map { cat -> cat.name }.toTypedArray()
            remoteFactsRepository.addCategory(*categories)
            val limit = random.nextInt(from = 1, until = listSize + 1) // until is exclusive
            val expected = remoteCategories.take(limit)
            // When
            val result = factsRepository.fetchCategories(limit = limit, shuffle = false)
            val compare = result.getOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get shuffled categories when shuffle is true`() =
        runBlockingTest(dispatcher) {
            // Given
            val listSize = 5
            val categories = List(listSize) { Category(name = "cat$it") }
            localFactsRepository.saveCategories(categories)
            // When
            val result = factsRepository.fetchCategories(limit = listSize, shuffle = true)
            val compare = result.getOrNull()
            // Then
            assertThat(compare)
                .hasSize(listSize)
                .isNotEqualTo(categories) // Two lists are only equals in size and items position
        }

    @Test
    fun `should get empty last searches when no one search was made`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = emptyList<LastSearch>()
            // When
            val result = factsRepository.getLastSearches()
            val compare = result.getOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get last searches when at least one search was made`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = List(5) { LastSearch(term = "term$it") }
            for (search in expected) {
                localFactsRepository.saveNewSearch(search)
            }
            // When
            val result = factsRepository.getLastSearches()
            val compare = result.getOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get a failure when getting last searches`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = Exception("from last searches")
            localFactsRepository.exceptionToThrow = expected
            // When
            val result = factsRepository.getLastSearches()
            val compare = result.exceptionOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get empty fact when searching not found term`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = emptyList<Fact>()
            // When
            val result = factsRepository.doSearch(text = "")
            val compare = result.getOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get a failure when searching for facts`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = Exception("from searching facts")
            remoteFactsRepository.exceptionToThrow = expected
            // When
            val result = factsRepository.doSearch(text = "any")
            val compare = result.exceptionOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get facts when searching term returns data`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = List(5) {
                Fact(
                    id = "$it",
                    value = "value$it",
                    url = "url$it",
                    categories = emptyList()
                )
            }
            val facts = expected.toTypedArray()
            remoteFactsRepository.addFact(*facts)
            // When
            val result = factsRepository.doSearch(text = "ajdf adfjad flalsdfj a")
            val compare = result.getOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should persist search term when searching term returns data`() =
        runBlockingTest(dispatcher) {
            // Given
            val lastSearch = LastSearch(term = "ajdf adfjad flalsdfj a")
            val expected = List(5) {
                Fact(
                    id = "$it",
                    value = "value$it",
                    url = "url$it",
                    categories = emptyList()
                )
            }
            val facts = expected.toTypedArray()
            remoteFactsRepository.addFact(*facts)
            // When
            val result = factsRepository.doSearch(text = lastSearch.term)
            val compare = result.getOrNull()
            val lastSearchesResult = factsRepository.getLastSearches()
            val lastSearches = lastSearchesResult.getOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
            assertThat(lastSearches).isEqualTo(listOf(lastSearch))
        }
}
