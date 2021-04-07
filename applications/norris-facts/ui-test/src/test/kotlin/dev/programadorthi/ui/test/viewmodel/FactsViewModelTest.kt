package dev.programadorthi.ui.test.viewmodel

import dev.programadorthi.norris.domain.FactsBusiness
import dev.programadorthi.norris.domain.fake.FactsUseCaseFake
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.ui.fake.provider.StyleProviderFake
import dev.programadorthi.norris.ui.model.FactViewData
import dev.programadorthi.norris.ui.viewmodel.FactsViewModel
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.fake.StringProviderFake
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class FactsViewModelTest {

    private val random = Random.Default
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var stringProvider: StringProviderFake
    private lateinit var styleProvider: StyleProviderFake
    private lateinit var factsUserCase: FactsUseCaseFake
    private lateinit var viewModel: FactsViewModel

    @Before
    fun `before each test`() {
        stringProvider = StringProviderFake()
        styleProvider = StyleProviderFake()
        factsUserCase = FactsUseCaseFake()
        viewModel = FactsViewModel(
            factsUseCase = factsUserCase,
            stringProvider = stringProvider,
            styleProvider = styleProvider,
            ioDispatcher = dispatcher
        )
    }

    @After
    fun `after each test`() {
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should start in Idle state when created`() = runBlockingTest(dispatcher) {
        // Given
        val expected = listOf<UIState<List<FactViewData>>>(UIState.Idle)
        val result = mutableListOf<UIState<List<FactViewData>>>()
        // When
        val job = launch { viewModel.facts().toList(result) }
        job.cancelAndJoin()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should get business state when searching for an invalid term`() =
        runBlockingTest(dispatcher) {
            // Given
            val cause = FactsBusiness.EmptySearch
            val message = "this is the message"
            val expected = listOf<UIState<List<FactViewData>>>(
                UIState.Idle,
                UIState.Loading,
                UIState.Business(
                    cause = cause,
                    message = message
                )
            )
            val result = mutableListOf<UIState<List<FactViewData>>>()
            // When
            stringProvider.stringToReturn = message
            factsUserCase.businessToResult = cause
            val job = launch { viewModel.facts().toList(result) }
            viewModel.search(text = "")
            job.cancelAndJoin()
            // Then
            assertThat(result).isEqualTo(expected)
        }

    @Test
    fun `should get error state when searching get a failure`() = runBlockingTest(dispatcher) {
        // Given
        val message = "this is the message"
        val throwable = Exception(message)
        val expected = listOf<UIState<List<FactViewData>>>(
            UIState.Idle,
            UIState.Loading,
            UIState.Error(
                cause = throwable,
                message = message
            )
        )
        val result = mutableListOf<UIState<List<FactViewData>>>()
        // When
        stringProvider.stringToReturn = message
        factsUserCase.exceptionResult = throwable
        val job = launch { viewModel.facts().toList(result) }
        viewModel.search(text = "some term")
        job.cancelAndJoin()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should get empty facts when searching found nothing`() = runBlockingTest(dispatcher) {
        // Given
        val expected = listOf<UIState<List<FactViewData>>>(
            UIState.Idle,
            UIState.Loading,
            UIState.Success(emptyList())
        )
        val result = mutableListOf<UIState<List<FactViewData>>>()
        // When
        val job = launch { viewModel.facts().toList(result) }
        viewModel.search(text = "some term")
        job.cancelAndJoin()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should get some uncategorized facts when searching returns some results`() =
        runBlockingTest(dispatcher) {
            // Given
            val category = "uncategorized"
            val headline = random.nextInt(1000)
            val facts = List(random.nextInt(from = 1, until = 10)) { index ->
                Fact(
                    id = "id$index",
                    url = "url$index",
                    value = "value$index",
                    categories = emptyList() // fact without categories is uncategorized
                )
            }
            val data = facts.map { fact ->
                FactViewData(
                    category = category,
                    style = headline,
                    url = fact.url,
                    value = fact.value
                )
            }
            val expected = listOf(
                UIState.Idle,
                UIState.Loading,
                UIState.Success(data)
            )
            // When
            stringProvider.stringToReturn = category
            styleProvider.headline = headline
            factsUserCase.addFact(*facts.toTypedArray())
            val result = mutableListOf<UIState<List<FactViewData>>>()
            val job = launch { viewModel.facts().toList(result) }
            viewModel.search(text = "some term")
            job.cancelAndJoin()
            // Then
            assertThat(result).isEqualTo(expected)
        }

    @Test
    fun `should get some categorized facts when searching returns some results`() =
        runBlockingTest(dispatcher) {
            // Given
            val categories = List(5) { index -> "cat$index" }
            val headline = random.nextInt(1000)
            val facts = List(random.nextInt(from = 1, until = 10)) { index ->
                Fact(
                    id = "id$index",
                    url = "url$index",
                    value = "value$index",
                    categories = categories
                )
            }
            val data = facts.map { fact ->
                FactViewData(
                    category = categories.first(),
                    style = headline,
                    url = fact.url,
                    value = fact.value
                )
            }
            val expected = listOf(
                UIState.Idle,
                UIState.Loading,
                UIState.Success(data)
            )
            // When
            styleProvider.headline = headline
            factsUserCase.addFact(*facts.toTypedArray())
            val result = mutableListOf<UIState<List<FactViewData>>>()
            val job = launch { viewModel.facts().toList(result) }
            viewModel.search(text = "some term")
            job.cancelAndJoin()
            // Then
            assertThat(result).isEqualTo(expected)
        }

    @Test
    fun `should get some headline facts when searching returns some results`() =
        runBlockingTest(dispatcher) {
            // Given
            val category = "category"
            val headline = random.nextInt(1000)
            val facts = List(random.nextInt(from = 1, until = 10)) { index ->
                Fact(
                    id = "id$index",
                    url = "url$index",
                    value = "value$index",
                    categories = emptyList()
                )
            }
            val data = facts.map { fact ->
                FactViewData(
                    category = category,
                    style = headline,
                    url = fact.url,
                    value = fact.value
                )
            }
            val expected = listOf(
                UIState.Idle,
                UIState.Loading,
                UIState.Success(data)
            )
            // When
            stringProvider.stringToReturn = category
            styleProvider.headline = headline
            factsUserCase.addFact(*facts.toTypedArray())
            val result = mutableListOf<UIState<List<FactViewData>>>()
            val job = launch { viewModel.facts().toList(result) }
            viewModel.search(text = "some term")
            job.cancelAndJoin()
            // Then
            assertThat(result).isEqualTo(expected)
        }

    @Test
    fun `should get some subtitle facts when searching returns some results`() =
        runBlockingTest(dispatcher) {
            // Given
            val category = "category"
            val subtitle = random.nextInt(1000)
            val facts = List(random.nextInt(from = 1, until = 10)) { index ->
                Fact(
                    id = "id$index",
                    url = "url$index",
                    value = "value$index",
                    categories = emptyList()
                )
            }
            val data = facts.map { fact ->
                FactViewData(
                    category = category,
                    style = subtitle,
                    url = fact.url,
                    value = fact.value
                )
            }
            val expected = listOf(
                UIState.Idle,
                UIState.Loading,
                UIState.Success(data)
            )
            // When
            stringProvider.stringToReturn = category
            styleProvider.subtitle = subtitle
            factsUserCase.addFact(*facts.toTypedArray())
            val result = mutableListOf<UIState<List<FactViewData>>>()
            val job = launch { viewModel.facts().toList(result) }
            viewModel.search(text = "some term")
            job.cancelAndJoin()
            // Then
            assertThat(result).isEqualTo(expected)
        }
}
