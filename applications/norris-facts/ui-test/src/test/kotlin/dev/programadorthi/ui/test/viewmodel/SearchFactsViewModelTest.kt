package dev.programadorthi.ui.test.viewmodel

import dev.programadorthi.norris.domain.fake.FactsUseCaseFake
import dev.programadorthi.norris.domain.model.Category
import dev.programadorthi.norris.domain.model.LastSearch
import dev.programadorthi.norris.ui.viewmodel.SearchFactsViewModel
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.fake.StringProviderFake
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Condition
import org.assertj.core.api.InstanceOfAssertFactories
import org.assertj.core.api.InstanceOfAssertFactory
import org.assertj.core.internal.Conditions
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class SearchFactsViewModelTest {

    private val random = Random.Default
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var stringProvider: StringProviderFake
    private lateinit var factsUserCase: FactsUseCaseFake
    private lateinit var viewModel: SearchFactsViewModel

    @Before
    fun `before each test`() {
        stringProvider = StringProviderFake()
        factsUserCase = FactsUseCaseFake()
        viewModel = SearchFactsViewModel(
            factsUseCase = factsUserCase,
            stringProvider = stringProvider,
            ioDispatcher = dispatcher
        )
    }

    @After
    fun `after each test`() {
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should categories start in Idle state when created`() = runBlockingTest(dispatcher) {
        // Given
        val expected = listOf<UIState<List<String>>>(UIState.Idle)
        val result = mutableListOf<UIState<List<String>>>()
        // When
        val job = launch { viewModel.categories().toList(result) }
        job.cancelAndJoin()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should last searches start in Idle state when created`() = runBlockingTest(dispatcher) {
        // Given
        val expected = listOf<UIState<List<String>>>(UIState.Idle)
        val result = mutableListOf<UIState<List<String>>>()
        // When
        val job = launch { viewModel.lastSearches().toList(result) }
        job.cancelAndJoin()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should categories get error state when listing on limit`() = runBlockingTest(dispatcher) {
        // Given
        val message = "categories error message"
        val throwable = Exception(message)
        val expected = listOf<UIState<List<String>>>(
            UIState.Idle,
            UIState.Error(
                cause = throwable,
                message = message
            )
        )
        val result = mutableListOf<UIState<List<String>>>()
        // When
        stringProvider.stringToReturn = message
        factsUserCase.exceptionResult = throwable
        val job = launch { viewModel.categories().toList(result) }
        viewModel.fetchCategories()
        job.cancelAndJoin()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should last searches get error state when listing all`() = runBlockingTest(dispatcher) {
        // Given
        val message = "last searches error message"
        val throwable = Exception(message)
        val expected = listOf<UIState<List<String>>>(
            UIState.Idle,
            UIState.Error(
                cause = throwable,
                message = message
            )
        )
        val result = mutableListOf<UIState<List<String>>>()
        // When
        stringProvider.stringToReturn = message
        factsUserCase.exceptionResult = throwable
        val job = launch { viewModel.lastSearches().toList(result) }
        viewModel.fetchLastSearches()
        job.cancelAndJoin()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should categories is empty when there is no categories`() = runBlockingTest(dispatcher) {
        // Given
        val expected = listOf<UIState<List<String>>>(
            UIState.Idle,
            UIState.Success(emptyList())
        )
        val result = mutableListOf<UIState<List<String>>>()
        // When
        val job = launch { viewModel.categories().toList(result) }
        viewModel.fetchCategories()
        job.cancelAndJoin()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should last searches is empty when user has not made a search`() =
        runBlockingTest(dispatcher) {
            // Given
            val expected = listOf<UIState<List<String>>>(
                UIState.Idle,
                UIState.Success(emptyList())
            )
            val result = mutableListOf<UIState<List<String>>>()
            // When
            val job = launch { viewModel.lastSearches().toList(result) }
            viewModel.fetchLastSearches()
            job.cancelAndJoin()
            // Then
            assertThat(result).isEqualTo(expected)
        }

    @Test
    fun `should get some categories when there is at least one or more`() =
        runBlockingTest(dispatcher) {
            // Given
            val categories = List(random.nextInt(from = 1, until = 20)) { index ->
                Category(name = "Cat$index")
            }
            val expected = categories.map { it.name }
            // When
            factsUserCase.addCategories(*categories.toTypedArray())
            val result = mutableListOf<UIState<List<String>>>()
            val job = launch { viewModel.categories().toList(result) }
            viewModel.fetchCategories()
            job.cancelAndJoin()
            // Then
            // TODO: test is very complex. Am I doing mistakes in some architecture point?
            assertThat(result)
                .first()
                .isEqualTo(UIState.Idle)
            assertThat(result)
                .last()
                .asInstanceOf(InstanceOfAssertFactories.type(UIState.Success::class.java))
                .extracting { success -> success.data }
                .asInstanceOf(InstanceOfAssertFactories.list(String::class.java))
                .hasSameElementsAs(expected) // cannot be isEqualTo because the list is shuffled by default
        }

    @Test
    fun `should get some last searches when there is at least one or more`() =
        runBlockingTest(dispatcher) {
            // Given
            val lastSearches = List(random.nextInt(from = 1, until = 20)) { index ->
                LastSearch(term = "Term$index")
            }
            val expected = listOf(
                UIState.Idle,
                UIState.Success(lastSearches.map { it.term })
            )
            // When
            factsUserCase.addLastSearches(*lastSearches.toTypedArray())
            val result = mutableListOf<UIState<List<String>>>()
            val job = launch { viewModel.lastSearches().toList(result) }
            viewModel.fetchLastSearches()
            job.cancelAndJoin()
            // Then
            assertThat(result).isEqualTo(expected)
        }
}
