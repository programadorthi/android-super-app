package dev.programadorthi.shared.ui.di.viewmodel

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dev.programadorthi.shared.domain.viewmodel.ViewModel
import javax.inject.Provider

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelEntryPoint {
    fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
}
