package dev.programadorthi.domain.test

import dev.programadorthi.norris.domain.FactsBusiness
import dev.programadorthi.norris.domain.fake.data.FactsRepositoryFake
import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.LastSearch
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.norris.domain.usecase.FactsUseCaseFactory
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class FactsUseCaseTest {

    private val factsRepositoryFake = FactsRepositoryFake()
    private lateinit var factsUseCase: FactsUseCase

    @Before
    fun `before each test`() {
        factsUseCase = FactsUseCaseFactory(
            factsRepository = factsRepositoryFake
        )
    }

    @Test
    fun `should get empty categories when limit is less than or equals to zero`() =
        runBlockingTest {
            // Given
            val expected = emptyList<Category>()
            // When
            val result = factsUseCase.categories(limit = 0, shuffle = false)
            val compare = result.getOrNull()
            // Then
            assertThat(compare).isEqualTo(expected)
        }

    @Test
    fun `should get categories when limit is greater than zero`() = runBlockingTest {
        // Given
        val listSize = 5
        val expected = List(listSize) { Category(name = "cat$it") }
        factsRepositoryFake.addCategory(*expected.toTypedArray())
        // When
        val result = factsUseCase.categories(limit = listSize, shuffle = false)
        val compare = result.getOrNull()
        // Then
        assertThat(compare).isEqualTo(expected)
    }

    @Test
    fun `should get shuffled categories when limit is greater than zero`() = runBlockingTest {
        // Given
        val listSize = 5
        val expected = List(listSize) { Category(name = "cat$it") }
        factsRepositoryFake.addCategory(*expected.toTypedArray())
        // When
        val result = factsUseCase.categories(limit = listSize, shuffle = true)
        val compare = result.getOrNull()
        // Then
        assertThat(compare)
            .hasSize(listSize)
            .isNotEqualTo(expected)
    }

    @Test
    fun `should get empty last searches when no one search was made`() = runBlockingTest {
        // Given
        val expected = emptyList<LastSearch>()
        // When
        val result = factsUseCase.lastSearches()
        val compare = result.getOrNull()
        // Then
        assertThat(compare).isEqualTo(expected)
    }

    @Test
    fun `should get last searches when at least one search was made`() = runBlockingTest {
        // Given
        val expected = List(5) { LastSearch(term = "term$it") }
        for (search in expected) {
            factsRepositoryFake.addLastSearch(search)
        }
        // When
        val result = factsUseCase.lastSearches()
        val compare = result.getOrNull()
        // Then
        assertThat(compare).isEqualTo(expected)
    }

    @Test
    fun `should get business validation when search term is blank`() = runBlockingTest {
        // Given
        val expected = FactsBusiness.EmptySearch
        // When
        val result = factsUseCase.search(text = " ")
        val compare = result.businessOrNull()
        // Then
        assertThat(compare).isEqualTo(expected)
    }

    @Test
    fun `should get facts when search term is valid`() = runBlockingTest {
        // Given
        val expected = List(5) {
            Fact(
                id = "$it",
                value = "value$it",
                url = "url$it",
                categories = emptyList()
            )
        }
        factsRepositoryFake.addFact(*expected.toTypedArray())
        // When
        val result = factsUseCase.search(text = "a b c d")
        val compare = result.getOrNull()
        // Then
        assertThat(compare).isEqualTo(expected)
    }
}
