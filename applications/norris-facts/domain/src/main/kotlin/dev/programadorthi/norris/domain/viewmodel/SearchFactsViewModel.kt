package dev.programadorthi.norris.domain.viewmodel

import dev.programadorthi.shared.domain.UIState
import kotlinx.coroutines.flow.StateFlow

interface SearchFactsViewModel {
    val categories: StateFlow<UIState<List<String>>>
    val lastSearches: StateFlow<UIState<List<String>>>

    suspend fun fetchCategories()
    suspend fun fetchLastSearches()
}
