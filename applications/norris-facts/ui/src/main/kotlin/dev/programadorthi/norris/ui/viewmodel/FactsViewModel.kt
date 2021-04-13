package dev.programadorthi.norris.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.programadorthi.norris.domain.FactsBusiness
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.norris.ui.model.FactViewData
import dev.programadorthi.norris.ui.provider.StyleProvider
import dev.programadorthi.shared.domain.Result
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.ext.toUIState
import dev.programadorthi.shared.ui.flow.PropertyStateFlow
import dev.programadorthi.shared.ui.resource.StringProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import dev.programadorthi.norris.ui.R as mainR

class FactsViewModel(
    private val factsUseCase: FactsUseCase,
    private val stringProvider: StringProvider,
    private val styleProvider: StyleProvider,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val mutableFacts = PropertyStateFlow<List<FactViewData>>()
    fun facts() = mutableFacts.stateFlow

    fun search(text: String) {
        viewModelScope.launch(ioDispatcher) {
            mutableFacts.loading()
            val result = factsUseCase.search(text)
            val nextState = result.toUIState(
                businessMessage = stringProvider.getString(result.businessToStringRes()),
                failureMessage = stringProvider.getString(mainR.string.something_wrong),
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
            ?: stringProvider.getString(mainR.string.item_fact_view_holder_uncategorized_label),
        url = fact.url,
        value = fact.value,
        style = styleProvider.provideHeadlineOrSubtitle {
            fact.value.length < HIGH_FONT_CHARACTERS_LIMIT
        }
    )

    private fun <T> Result<T>.businessToStringRes(): Int =
        when (this.businessOrNull()) {
            is FactsBusiness.EmptySearch -> mainR.string.activity_facts_empty_search_term
            else -> mainR.string.something_wrong
        }

    private companion object {
        private const val HIGH_FONT_CHARACTERS_LIMIT = 80
    }
}
