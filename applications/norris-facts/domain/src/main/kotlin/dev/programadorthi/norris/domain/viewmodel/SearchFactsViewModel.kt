package dev.programadorthi.norris.domain.viewmodel

import dev.programadorthi.shared.domain.UIState
import dev.programadorthi.shared.domain.viewmodel.ViewModel
import kotlinx.coroutines.flow.StateFlow

interface SearchFactsViewModel : ViewModel {
    val categories: StateFlow<UIState<List<String>>>
    val lastSearches: StateFlow<UIState<List<String>>>

    fun fetchCategories()
    fun fetchLastSearches()
}
