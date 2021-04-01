package dev.programadorthi.norris.ui.ext

import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import org.kodein.di.DIAware
import org.kodein.di.direct
import org.kodein.di.instance

inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : DIAware, T : FragmentActivity =
    viewModels(factoryProducer = { direct.instance() })
