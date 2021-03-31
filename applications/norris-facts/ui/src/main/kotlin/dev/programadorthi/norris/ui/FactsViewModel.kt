package dev.programadorthi.norris.ui

import androidx.lifecycle.ViewModel
import dev.programadorthi.norris.domain.FactsBusiness
import dev.programadorthi.norris.domain.model.Fact
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.norris.ui.model.FactViewData
import dev.programadorthi.shared.domain.Result
import dev.programadorthi.shared.domain.getOrDefault
import dev.programadorthi.shared.domain.resource.StringProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.google.android.material.R as materialR
import dev.programadorthi.norris.ui.R as mainR

class FactsViewModel(
    private val factsUseCase: FactsUseCase,
    private val stringProvider: StringProvider,
    private val ioScope: CoroutineScope
) : ViewModel() {
    private val mutableFacts = OurStateFlow<List<FactViewData>>()
    fun facts() = mutableFacts.stateFlow

    fun search(text: String) {
        ioScope.launch {
            mutableFacts.update(UIState.Loading)
            val result = factsUseCase.search(text)
            val nextState = when {
                result is Result.Business -> UIState.Business(
                    cause = result,
                    message = stringProvider.getString(result.toStringRes())
                )
                result.isFailure -> UIState.Error(
                    cause = result.exceptionOrNull(),
                    message = stringProvider.getString(mainR.string.something_wrong)
                )
                else -> result
                    .getOrDefault(emptyList())
                    .map(::mapFact)
                    .let { facts -> UIState.Success(facts) }
            }
            mutableFacts.update(nextState)
        }
    }

    private fun mapFact(fact: Fact) = FactViewData(
        category = fact.categories.firstOrNull()
            ?: stringProvider.getString(mainR.string.item_fact_view_holder_uncategorized_label),
        url = fact.url,
        value = fact.value,
        style = if (fact.value.length > HIGH_FONT_CHARACTERS_LIMIT) {
            materialR.style.TextAppearance_MaterialComponents_Subtitle1
        } else {
            materialR.style.TextAppearance_MaterialComponents_Headline4
        }
    )

    private fun Result.Business.toStringRes(): Int =
        when (this) {
            is FactsBusiness.EmptySearch -> mainR.string.activity_facts_empty_search_term
            else -> mainR.string.something_wrong
        }

    private companion object {
        private const val HIGH_FONT_CHARACTERS_LIMIT = 80
    }
}
