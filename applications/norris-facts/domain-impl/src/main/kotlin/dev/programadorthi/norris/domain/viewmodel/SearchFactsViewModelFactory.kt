package dev.programadorthi.norris.domain.viewmodel

import dev.programadorthi.norris.domain.usecase.FactsUseCase
import dev.programadorthi.shared.domain.provider.SharedTextProvider
import kotlinx.coroutines.CoroutineDispatcher

object SearchFactsViewModelFactory {
    operator fun invoke(
        factsUseCase: FactsUseCase,
        sharedTextProvider: SharedTextProvider,
        ioDispatcher: CoroutineDispatcher
    ): SearchFactsViewModel = SearchFactsViewModelImpl(
        factsUseCase = factsUseCase,
        sharedTextProvider = sharedTextProvider,
        ioDispatcher = ioDispatcher
    )
}
