package dev.programadorthi.norris.ui.component

import android.view.View
import android.widget.Toast
import dev.programadorthi.shared.ui.UIState
import dev.programadorthi.shared.ui.ext.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: maybe move to an UI module
class ErrorComponent(
    uiState: Flow<UIState<*>>,
    view: View
) {
    init {
        view.lifecycleScope.launch {
            uiState.collect { state ->
                val message = when (state) {
                    is UIState.Business -> state.message
                    is UIState.Error -> state.message
                    else -> ""
                }
                if (message.isNotBlank()) {
                    // FIXME: using toast here but it could be a TextView
                    Toast.makeText(view.context, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
