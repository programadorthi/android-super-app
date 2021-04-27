package dev.programadorthi.norris.domain.viewmodel

import dev.programadorthi.norris.domain.model.presentation.FactViewData
import dev.programadorthi.shared.domain.UIState
import dev.programadorthi.shared.domain.viewmodel.ViewModel
import kotlinx.coroutines.flow.StateFlow

interface FactsViewModel : ViewModel {
    val facts: StateFlow<UIState<List<FactViewData>>>

    fun search(text: String)
}
