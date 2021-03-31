package dev.programadorthi.norris.ui

import dev.programadorthi.shared.domain.Result

// TODO: move to a module
sealed class UIState<out T> {
    object Idle : UIState<Nothing>()
    object Loading : UIState<Nothing>()
    data class Error(val cause: Throwable?, val message: String) : UIState<Nothing>()
    data class Business(val cause: Result.Business, val message: String) : UIState<Nothing>()
    data class Success<R>(val data: R) : UIState<R>()
}
