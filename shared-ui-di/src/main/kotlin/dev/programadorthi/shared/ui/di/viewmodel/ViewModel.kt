package dev.programadorthi.shared.ui.di.viewmodel

import androidx.activity.ComponentActivity
import dagger.hilt.android.EntryPointAccessors
import dev.programadorthi.shared.domain.viewmodel.ViewModel
import dev.programadorthi.shared.ui.viewmodel.ViewModelFactory
import dev.programadorthi.shared.ui.viewmodel.ViewModelLazy

inline fun <reified VM, T> T.viewModel(): Lazy<VM> where VM : ViewModel, T : ComponentActivity =
    ViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { viewModelStore },
        factoryProducer = {
            ViewModelFactory(
                owner = this,
                defaultArgs = intent.extras,
                viewModel = {
                    val viewModels = EntryPointAccessors
                        .fromActivity(this, ViewModelEntryPoint::class.java)
                        .viewModels()
                    val viewModel = viewModels[VM::class.java]?.get()
                        ?: throw IllegalStateException("${VM::class} not provided by Hilt")
                    viewModel as VM
                } // Lazy to avoid create an instance without needed
            )
        }
    )
