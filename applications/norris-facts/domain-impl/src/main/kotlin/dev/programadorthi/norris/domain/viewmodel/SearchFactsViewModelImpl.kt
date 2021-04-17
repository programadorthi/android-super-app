package dev.programadorthi.norris.domain.viewmodel

import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.shared.domain.UIState
import dev.programadorthi.shared.domain.exception.NetworkingError
import dev.programadorthi.shared.domain.flow.PropertyUIStateFlow
import dev.programadorthi.shared.domain.getOrDefault
import dev.programadorthi.shared.domain.provider.SharedTextProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

internal class SearchFactsViewModelImpl(
    private val factsUseCase: FactsUseCase,
    private val sharedTextProvider: SharedTextProvider,
    private val ioDispatcher: CoroutineDispatcher
) : SearchFactsViewModel {
    private val mutableCategories = PropertyUIStateFlow<List<String>>()
    private val mutableLastSearches = PropertyUIStateFlow<List<String>>()

    override val categories: StateFlow<UIState<List<String>>>
        get() = mutableCategories.stateFlow

    override val lastSearches: StateFlow<UIState<List<String>>>
        get() = mutableLastSearches.stateFlow

    override suspend fun fetchCategories() {
        withContext(ioDispatcher) {
            val result = factsUseCase.categories(limit = MAX_VISIBLE_CATEGORIES, shuffle = true)
            val nextState = when {
                result.isFailure -> UIState.Error(
                    cause = result.exceptionOrNull(),
                    message = result.exceptionOrNull().toTextMessage()
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

    override suspend fun fetchLastSearches() {
        withContext(ioDispatcher) {
            val result = factsUseCase.lastSearches()
            val nextState = when {
                result.isFailure -> UIState.Error(
                    cause = result.exceptionOrNull(),
                    message = result.exceptionOrNull().toTextMessage()
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

    private fun Throwable?.toTextMessage() =
        when (this) {
            is NetworkingError.NoInternetConnection -> sharedTextProvider.noInternetConnection()
            else -> sharedTextProvider.somethingWrong()
        }

    private companion object {
        private const val MAX_VISIBLE_CATEGORIES = 8
    }
}
