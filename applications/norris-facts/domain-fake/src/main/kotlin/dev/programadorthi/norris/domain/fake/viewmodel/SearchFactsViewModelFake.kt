package dev.programadorthi.norris.domain.fake.viewmodel

import dev.programadorthi.norris.domain.viewmodel.SearchFactsViewModel
import dev.programadorthi.shared.domain.UIState
import dev.programadorthi.shared.domain.flow.PropertyUIStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchFactsViewModelFake : SearchFactsViewModel {
    private val mutableCategories = PropertyUIStateFlow<List<String>>()
    private val mutableLastSearches = PropertyUIStateFlow<List<String>>()

    override val categories: StateFlow<UIState<List<String>>>
        get() = mutableCategories.stateFlow

    override val lastSearches: StateFlow<UIState<List<String>>>
        get() = mutableLastSearches.stateFlow

    override suspend fun fetchCategories() {
        // no-op
    }

    override suspend fun fetchLastSearches() {
        // no-op
    }

    fun addCategoryState(state: UIState<List<String>>) {
        mutableCategories.update(state)
    }

    fun addLastSearchesState(state: UIState<List<String>>) {
        mutableLastSearches.update(state)
    }
}
