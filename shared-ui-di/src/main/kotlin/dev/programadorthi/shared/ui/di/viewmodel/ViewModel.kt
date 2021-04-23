package dev.programadorthi.shared.ui.di.viewmodel

import androidx.activity.ComponentActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.EntryPointAccessors
import dev.programadorthi.shared.domain.viewmodel.ViewModel
import dev.programadorthi.shared.ui.viewmodel.ViewModelContainer
import dev.programadorthi.shared.ui.viewmodel.ViewModelFactory

inline fun <reified VM, T> T.viewModel(): Lazy<VM> where VM : ViewModel, T : ComponentActivity =
    lazy {
        val vmClass = VM::class.java
        val factory = ViewModelFactory(
            owner = this,
            defaultArgs = bundleOf(),
            viewModel = {
                val viewModels = EntryPointAccessors
                    .fromActivity(this, ViewModelEntryPoint::class.java)
                    .viewModels()
                val viewModel = viewModels[vmClass]?.get()
                    ?: throw IllegalStateException("$vmClass not provided by Hilt")
                viewModel as VM
            } // Lazy to avoid create an instance without needed
        )
        val provider = ViewModelProvider(viewModelStore, factory)
        val viewModel = provider
            .get(vmClass.canonicalName, ViewModelContainer::class.java)
            .viewModel
        viewModel as? VM
            ?: throw IllegalStateException("${VM::class} not created/found on ViewModelStore")
    }
