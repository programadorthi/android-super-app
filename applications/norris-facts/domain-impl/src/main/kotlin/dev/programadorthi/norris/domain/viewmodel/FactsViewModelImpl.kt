package dev.programadorthi.norris.domain.viewmodel

import dev.programadorthi.norris.domain.FactsBusiness
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.model.presentation.FactViewData
import dev.programadorthi.norris.domain.provider.FactsStyleProvider
import dev.programadorthi.norris.domain.provider.FactsTextProvider
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.shared.domain.Result
import dev.programadorthi.shared.domain.UIState
import dev.programadorthi.shared.domain.ext.toUIState
import dev.programadorthi.shared.domain.flow.PropertyUIStateFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

internal class FactsViewModelImpl(
    private val factsUseCase: FactsUseCase,
    private val factsTextProvider: FactsTextProvider,
    private val factsStyleProvider: FactsStyleProvider,
    private val ioDispatcher: CoroutineDispatcher
) : FactsViewModel {
    private val mutableFacts = PropertyUIStateFlow<List<FactViewData>>()
    override val facts: StateFlow<UIState<List<FactViewData>>>
        get() = mutableFacts.stateFlow

    override suspend fun search(text: String) {
        withContext(ioDispatcher) {
            mutableFacts.loading()
            val result = factsUseCase.search(text)
            val nextState = result.toUIState(
                businessMessage = result.businessToTextMessage(),
                failureMessage = factsTextProvider.generalFailure(),
                successMapper = ::successMapper
            )
            mutableFacts.update(nextState)
        }
    }

    private fun successMapper(
        facts: List<Fact>?
    ): UIState.Success<List<FactViewData>> {
        val content = (facts ?: emptyList()).map(::mapFact)
        return UIState.Success(content)
    }

    private fun mapFact(fact: Fact) = FactViewData(
        category = fact.categories.firstOrNull()
            ?: factsTextProvider.withoutCategory(),
        url = fact.url,
        value = fact.value,
        style = factsStyleProvider.provideHeadlineOrSubtitle {
            fact.value.length < HIGH_FONT_CHARACTERS_LIMIT
        }
    )

    private fun <T> Result<T>.businessToTextMessage(): String =
        when (this.businessOrNull()) {
            is FactsBusiness.EmptySearch -> factsTextProvider.emptySearchTerm()
            else -> factsTextProvider.generalFailure()
        }

    private companion object {
        private const val HIGH_FONT_CHARACTERS_LIMIT = 80
    }
}
