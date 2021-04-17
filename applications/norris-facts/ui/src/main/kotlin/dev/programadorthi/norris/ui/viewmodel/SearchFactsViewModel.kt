package dev.programadorthi.norris.ui.viewmodel

import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.shared.domain.exception.NetworkingError
import dev.programadorthi.shared.domain.getOrDefault
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.flow.PropertyUIStateFlow
import dev.programadorthi.shared.ui.resource.StringProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import dev.programadorthi.norris.ui.R as mainR

class SearchFactsViewModel(
    private val factsUseCase: FactsUseCase,
    private val stringProvider: StringProvider,
    private val ioDispatcher: CoroutineDispatcher
) {
    private val mutableCategories = PropertyUIStateFlow<List<String>>()
    fun categories() = mutableCategories.stateFlow

    private val mutableLastSearches = PropertyUIStateFlow<List<String>>()
    fun lastSearches() = mutableLastSearches.stateFlow

    suspend fun fetchCategories() {
        withContext(ioDispatcher) {
            val result = factsUseCase.categories(limit = MAX_VISIBLE_CATEGORIES, shuffle = true)
            val nextState = when {
                result.isFailure -> UIState.Error(
                    cause = result.exceptionOrNull(),
                    message = stringProvider.getString(result.exceptionOrNull().toStringRes())
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

    suspend fun fetchLastSearches() {
        withContext(ioDispatcher) {
            val result = factsUseCase.lastSearches()
            val nextState = when {
                result.isFailure -> UIState.Error(
                    cause = result.exceptionOrNull(),
                    message = stringProvider.getString(result.exceptionOrNull().toStringRes())
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
