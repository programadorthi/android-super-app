package dev.programadorthi.norris.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.shared.domain.Result
import dev.programadorthi.shared.domain.exception.NetworkingError
import dev.programadorthi.shared.domain.getOrDefault
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.flow.PropertyStateFlow
import dev.programadorthi.shared.ui.resource.StringProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import dev.programadorthi.norris.ui.R as mainR

class SearchFactsViewModel(
    private val factsUseCase: FactsUseCase,
    private val stringProvider: StringProvider,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val mutableCategories = PropertyStateFlow<List<String>>()
    fun categories() = mutableCategories.stateFlow

    private val mutableLastSearches = PropertyStateFlow<List<String>>()
    fun lastSearches() = mutableLastSearches.stateFlow

    fun fetchCategories() {
        viewModelScope.launch(ioDispatcher) {
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
        viewModelScope.launch(ioDispatcher) {
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
