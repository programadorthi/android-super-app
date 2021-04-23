package dev.programadorthi.shared.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.savedstate.SavedStateRegistryOwner
import dev.programadorthi.shared.domain.viewmodel.ViewModel
import androidx.lifecycle.ViewModel as AACViewModel

class ViewModelFactory<R : ViewModel>(
    private val viewModel: () -> R,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : AACViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = ViewModelContainer(viewModel.invoke()) as T
}
