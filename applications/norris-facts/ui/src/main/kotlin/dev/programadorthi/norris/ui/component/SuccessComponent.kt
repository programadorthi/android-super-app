package dev.programadorthi.norris.ui.component

import androidx.recyclerview.widget.RecyclerView
import dev.programadorthi.norris.domain.model.presentation.FactViewData
import dev.programadorthi.norris.ui.adapter.FactsAdapter
import dev.programadorthi.shared.domain.UIState
import dev.programadorthi.shared.ui.ext.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// Feature specific component. Avoid move to an UI module
class SuccessComponent(
    uiState: Flow<UIState<List<FactViewData>>>,
    view: RecyclerView,
    shareFact: (FactViewData) -> Unit,
    onEmptyDataSet: () -> Unit
) {
    private val factsAdapter = FactsAdapter(shareFact)

    init {
        view.adapter = factsAdapter
        view.lifecycleScope.launch {
            uiState.collect { state ->
                if (state is UIState.Loading) {
                    factsAdapter.update(emptyList())
                }
                if (state is UIState.Success) {
                    factsAdapter.update(state.data)
                    if (state.data.isEmpty()) {
                        onEmptyDataSet.invoke()
                    }
                }
            }
        }
    }
}
