package dev.programadorthi.norris.domain.viewmodel

import dev.programadorthi.norris.domain.provider.FactsStyleProvider
import dev.programadorthi.norris.domain.provider.FactsTextProvider
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.shared.domain.viewmodel.ViewModelScope

object FactsViewModelFactory {
    operator fun invoke(
        factsUseCase: FactsUseCase,
        factsTextProvider: FactsTextProvider,
        factsStyleProvider: FactsStyleProvider,
        viewModelScope: ViewModelScope
    ): FactsViewModel = FactsViewModelImpl(
        factsUseCase = factsUseCase,
        factsTextProvider = factsTextProvider,
        factsStyleProvider = factsStyleProvider,
        viewModelScope = viewModelScope
    )
}
