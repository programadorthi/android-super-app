package dev.programadorthi.norris.ui.component

import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.view.children
import com.google.android.material.chip.ChipGroup
import dev.programadorthi.norris.ui.R
import dev.programadorthi.shared.domain.UIState
import dev.programadorthi.shared.ui.ext.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChipsComponent(
    uiState: Flow<UIState<List<String>>>,
    private val view: ChipGroup,
    private val onChipClicked: (String) -> Unit,
    private val hasChipsListener: (Boolean) -> Unit
) {
    init {
        view.lifecycleScope.launch {
            uiState.collect { state ->
                val chips = when (state) {
                    is UIState.Success -> state.data
                    else -> emptyList()
                }
                loadChips(chips)
            }
        }
    }

    private fun loadChips(items: List<String>) {
        view.removeAllViews()
        val inflater = LayoutInflater.from(view.context)
        for (category in items) {
            // Because we passing view as root, inflate function returns it instead of inflated layout
            val chipGroup = inflater.inflate(R.layout.item_search_fact_category, view) as ChipGroup
            // Inflate function also call addView when passing a root. So in the ChipGroup our inflated layout is the last added view
            val chip = chipGroup.children.last() as TextView
            chip.text = category
            chip.setOnClickListener { onChipClicked(category) }
        }
        hasChipsListener.invoke(items.isNotEmpty())
    }
}
