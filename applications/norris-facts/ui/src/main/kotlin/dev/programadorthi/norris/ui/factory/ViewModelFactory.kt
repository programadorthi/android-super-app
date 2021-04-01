package dev.programadorthi.norris.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DI
import org.kodein.di.direct
import org.kodein.type.erased

class ViewModelFactory(private val di: DI) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        di.direct.Instance(erased(modelClass))
}
