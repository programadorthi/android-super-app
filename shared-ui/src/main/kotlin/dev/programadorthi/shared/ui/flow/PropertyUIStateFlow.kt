package dev.programadorthi.shared.ui.flow

import dev.programadorthi.shared.ui.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PropertyUIStateFlow<T> {
    private val mutableStateFlow = MutableStateFlow<UIState<T>>(UIState.Idle)
    val stateFlow: StateFlow<UIState<T>>
        get() = mutableStateFlow.asStateFlow()

    fun loading() {
        mutableStateFlow.value = UIState.Loading
    }

    fun update(value: UIState<T>) {
        mutableStateFlow.value = value
    }
}
