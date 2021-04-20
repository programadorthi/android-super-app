package dev.programadorthi.shared.domain.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

class ViewModelScope(
    override val coroutineContext: CoroutineContext
) : ViewModel, CoroutineScope {
    override fun dispose() = cancel()
}
