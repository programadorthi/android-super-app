package dev.programadorthi.shared.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import dev.programadorthi.shared.domain.viewmodel.ViewModel
import kotlin.reflect.KClass

/**
 * Extracted from original AAC [androidx.lifecycle.ViewModelLazy]
 */
class ViewModelLazy<VM : ViewModel>(
    private val viewModelClass: KClass<VM>,
    private val storeProducer: () -> ViewModelStore,
    private val factoryProducer: () -> ViewModelProvider.Factory
) : Lazy<VM> {
    private var cached: VM? = null

    override val value: VM
        get() = when (cached) {
            is VM -> cached!!
            else -> createOne()
        }

    override fun isInitialized(): Boolean = cached != null

    @Suppress("UNCHECKED_CAST")
    private fun createOne(): VM {
        val provider = ViewModelProvider(storeProducer.invoke(), factoryProducer.invoke())
        val viewModel = provider
            .get(viewModelClass.java.canonicalName!!, ViewModelContainer::class.java)
            .viewModel
        return viewModel as? VM
            ?: throw IllegalStateException("$viewModelClass not created/found on ViewModelStore")
    }
}
