package dev.programadorthi.norris.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.programadorthi.norris.domain.viewmodel.FactsViewModel
import javax.inject.Inject

@HiltViewModel
class FactsViewModelWrapper @Inject constructor(
    val viewModel: FactsViewModel
) : ViewModel()
