package dev.programadorthi.shared.ui.viewmodel

import dev.programadorthi.shared.domain.viewmodel.ViewModel
import androidx.lifecycle.ViewModel as AACViewModel

class ViewModelContainer<T : ViewModel>(
    val viewModel: T
) : AACViewModel() {
    override fun onCleared() {
        super.onCleared()
        viewModel.dispose()
    }
}
