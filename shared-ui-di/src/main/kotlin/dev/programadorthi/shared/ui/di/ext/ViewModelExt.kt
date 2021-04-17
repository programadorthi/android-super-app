package dev.programadorthi.shared.ui.di.ext

import androidx.activity.ComponentActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import dev.programadorthi.shared.ui.viewmodel.ViewModelContainer
import dev.programadorthi.shared.ui.viewmodel.ViewModelFactory
import org.kodein.di.DIAware
import org.kodein.di.direct
import org.kodein.di.instance

inline fun <reified VM, T> T.viewModel(): Lazy<VM> where T : DIAware, T : ComponentActivity = lazy {
    val factory = ViewModelFactory<VM>(
        viewModel = di.direct.instance(),
        owner = this,
        defaultArgs = bundleOf()
    )
    val provider = ViewModelProvider(viewModelStore, factory)
    val viewModel = provider
        .get(VM::class.java.canonicalName, ViewModelContainer::class.java)
        .viewModel
    viewModel as? VM
        ?: throw IllegalStateException("${VM::class} not created/found on ViewModelStore")
}
