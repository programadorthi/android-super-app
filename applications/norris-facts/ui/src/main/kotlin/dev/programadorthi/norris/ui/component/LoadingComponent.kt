package dev.programadorthi.norris.ui.component

import android.view.View
import androidx.core.view.isVisible
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.ext.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: maybe move to an UI module
class LoadingComponent(
    uiState: StateFlow<UIState<*>>,
    view: View
) {
    init {
        view.lifecycleScope.launch {
            uiState.collect { state ->
                view.isVisible = state is UIState.Loading
            }
        }
    }
}
