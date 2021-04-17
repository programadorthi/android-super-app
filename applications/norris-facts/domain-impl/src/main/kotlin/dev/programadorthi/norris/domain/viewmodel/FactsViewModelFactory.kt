package dev.programadorthi.norris.domain.viewmodel

import dev.programadorthi.norris.domain.provider.FactsStyleProvider
import dev.programadorthi.norris.domain.provider.FactsTextProvider
import dev.programadorthi.norris.domain.usecase.FactsUseCase
import kotlinx.coroutines.CoroutineDispatcher

object FactsViewModelFactory {
    operator fun invoke(
        factsUseCase: FactsUseCase,
        factsTextProvider: FactsTextProvider,
        factsStyleProvider: FactsStyleProvider,
        ioDispatcher: CoroutineDispatcher
    ): FactsViewModel = FactsViewModelImpl(
        factsUseCase = factsUseCase,
        factsTextProvider = factsTextProvider,
        factsStyleProvider = factsStyleProvider,
        ioDispatcher = ioDispatcher
    )
}
