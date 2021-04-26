package dev.programadorthi.norris.domain.fake.viewmodel

import dev.programadorthi.norris.domain.model.presentation.FactViewData
import dev.programadorthi.norris.domain.viewmodel.FactsViewModel
import dev.programadorthi.shared.domain.UIState
import dev.programadorthi.shared.domain.flow.PropertyUIStateFlow
import kotlinx.coroutines.flow.StateFlow

class FactsViewModelFake : FactsViewModel {
    private val mutableFacts = PropertyUIStateFlow<List<FactViewData>>()
    override val facts: StateFlow<UIState<List<FactViewData>>>
        get() = mutableFacts.stateFlow

    override fun search(text: String) {
        // no-op by default
    }

    override fun dispose() {
        // no-op by default
    }

    fun addFactState(state: UIState<List<FactViewData>>) {
        mutableFacts.update(state)
    }
}
