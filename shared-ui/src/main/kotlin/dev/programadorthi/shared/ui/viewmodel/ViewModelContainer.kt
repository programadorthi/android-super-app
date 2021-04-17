package dev.programadorthi.shared.ui.viewmodel

import androidx.lifecycle.ViewModel

class ViewModelContainer<T>(
    val viewModel: T
) : ViewModel()
