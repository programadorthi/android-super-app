package dev.programadorthi.norris.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.programadorthi.norris.domain.viewmodel.SearchFactsViewModel
import javax.inject.Inject

@HiltViewModel
class SearchFactsViewModelWrapper @Inject constructor(
    val viewModel: SearchFactsViewModel
) : ViewModel()
