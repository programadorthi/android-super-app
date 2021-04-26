package dev.programadorthi.norris.domain.viewmodel

import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.shared.domain.provider.SharedTextProvider
import dev.programadorthi.shared.domain.viewmodel.ViewModelScope

object SearchFactsViewModelFactory {
    operator fun invoke(
        factsUseCase: FactsUseCase,
        sharedTextProvider: SharedTextProvider,
        viewModelScope: ViewModelScope
    ): SearchFactsViewModel = SearchFactsViewModelImpl(
        factsUseCase = factsUseCase,
        sharedTextProvider = sharedTextProvider,
        viewModelScope = viewModelScope
    )
}
