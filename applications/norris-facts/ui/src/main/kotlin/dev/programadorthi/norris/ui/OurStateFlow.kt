package dev.programadorthi.norris.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OurStateFlow<T> {
    private val mutableStateFlow = MutableStateFlow<UIState<T>>(UIState.Idle)
    val stateFlow: StateFlow<UIState<T>>
        get() = mutableStateFlow.asStateFlow()

    fun update(value: UIState<T>) {
        mutableStateFlow.value = value
    }
}
