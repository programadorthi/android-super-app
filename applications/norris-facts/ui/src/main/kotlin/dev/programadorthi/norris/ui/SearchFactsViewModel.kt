package dev.programadorthi.norris.ui

import androidx.lifecycle.ViewModel
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.shared.domain.Result
import dev.programadorthi.shared.domain.exception.NetworkingError
import dev.programadorthi.shared.domain.getOrDefault
import dev.programadorthi.shared.domain.resource.StringProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import dev.programadorthi.norris.ui.R as mainR

class SearchFactsViewModel(
    private val factsUseCase: FactsUseCase,
    private val stringProvider: StringProvider,
    private val ioScope: CoroutineScope
) : ViewModel() {
    private val mutableCategories = OurStateFlow<List<String>>()
    fun categories() = mutableCategories.stateFlow

    private val mutableLastSearches = OurStateFlow<List<String>>()
    fun lastSearches() = mutableLastSearches.stateFlow

    fun fetchCategories() {
        ioScope.launch {
            val result = factsUseCase.categories(limit = MAX_VISIBLE_CATEGORIES, shuffle = true)
            val nextState = when {
                result is Result.Business -> UIState.Business(
                    result,
                    stringProvider.getString(mainR.string.empty_text)
                )
                result.isFailure -> UIState.Error(
                    result.exceptionOrNull(),
                    stringProvider.getString(result.exceptionOrNull().toStringRes())
                )
                else ->
                    result
                        .getOrDefault(emptyList())
                        .map { category -> category.name }
                        .let { categories -> UIState.Success(categories) }
            }
            mutableCategories.update(nextState)
        }
    }

    fun fetchLastSearches() {
        ioScope.launch {
            val result = factsUseCase.lastSearches()
            val nextState = when {
                result.isFailure -> UIState.Error(
                    result.exceptionOrNull(),
                    stringProvider.getString(result.exceptionOrNull().toStringRes())
                )
                else ->
                    result
                        .getOrDefault(emptyList())
                        .map { search -> search.term }
                        .let { searches -> UIState.Success(searches) }
            }
            mutableLastSearches.update(nextState)
        }
    }

    private fun Throwable?.toStringRes() =
        when (this) {
            is NetworkingError.NoInternetConnection -> mainR.string.no_internet_connection
            else -> mainR.string.something_wrong
        }

    private companion object {
        private const val MAX_VISIBLE_CATEGORIES = 8
    }
}
