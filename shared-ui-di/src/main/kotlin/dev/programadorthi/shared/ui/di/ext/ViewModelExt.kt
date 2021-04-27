package dev.programadorthi.shared.ui.di.ext

import androidx.activity.ComponentActivity
import dev.programadorthi.shared.domain.viewmodel.ViewModel
import dev.programadorthi.shared.ui.viewmodel.ViewModelFactory
import dev.programadorthi.shared.ui.viewmodel.ViewModelLazy
import org.kodein.di.DIAware
import org.kodein.di.direct
import org.kodein.type.erased

inline fun <reified VM, T> T.viewModel(): Lazy<VM> where VM : ViewModel, T : DIAware, T : ComponentActivity =
    ViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { viewModelStore },
        factoryProducer = {
            ViewModelFactory<VM>(
                viewModel = { di.direct.Instance(erased()) }, // Lazy to avoid create an instance without needed
                owner = this,
                defaultArgs = intent.extras
            )
        }
    )
