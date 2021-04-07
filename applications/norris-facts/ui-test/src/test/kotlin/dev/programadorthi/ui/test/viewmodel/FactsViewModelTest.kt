package dev.programadorthi.ui.test.viewmodel

import dev.programadorthi.norris.domain.fake.FactsUseCaseFake
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.usecase.FactsUseCase
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

    /*private val random = Random.Default
    private val dispatcher = TestCoroutineDispatcher()
    private val stringProvider = StringProviderFake()
    private lateinit var factsUserCase: FactsUseCase
    private lateinit var viewModel: FactsViewModel

    @Before
    fun `before each test`() {
        factsUserCase = FactsUseCaseFake()
        viewModel = FactsViewModel(
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
        viewModel.search(text = "")
        job.cancelAndJoin()
        // Then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should get some facts when searching returns some results`() = runBlockingTest(dispatcher) {
        val category = "category"
        val facts = List(random.nextInt(from = 1, until = 10)) { index ->
            Fact(
                id ="id$index",
                url = "url$index",
                value = "value$index",
                categories = emptyList()
            )
        }
        val data = facts.map { fact ->
            FactViewData(
                category = category,
            )
        }
        val expected = listOf<UIState<List<FactViewData>>>(
            UIState.Idle,
            UIState.Loading,
            UIState.Success(emptyList())
        )
        val result = mutableListOf<UIState<List<FactViewData>>>()
        val job = launch { viewModel.facts().toList(result) }
        viewModel.search(text = "")
        job.cancelAndJoin()
        assertThat(result).isEqualTo(expected)
    }*/
}
