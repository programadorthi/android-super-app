package dev.programadorthi.norris.domain.viewmodel

import dev.programadorthi.norris.domain.model.presentation.FactViewData
import dev.programadorthi.shared.domain.UIState
import kotlinx.coroutines.flow.StateFlow

interface FactsViewModel {
    val facts: StateFlow<UIState<List<FactViewData>>>

    suspend fun search(text: String)
}
