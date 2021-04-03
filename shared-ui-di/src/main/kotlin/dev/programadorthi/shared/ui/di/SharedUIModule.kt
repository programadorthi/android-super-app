package dev.programadorthi.shared.ui.di

import androidx.lifecycle.ViewModelProvider
import dev.programadorthi.shared.domain.DomainInjectionTags
import dev.programadorthi.shared.ui.di.factory.ViewModelFactory
import dev.programadorthi.shared.ui.network.ConnectionCheckFactory
import dev.programadorthi.shared.ui.report.CrashReportFactory
import dev.programadorthi.shared.ui.resource.StringProviderFactory
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object SharedUIModule {
    operator fun invoke() = DI.Module("shared-ui") {
        bindProvider(DomainInjectionTags.IO_DISPATCHER) { Dispatchers.IO }
        bindProvider<ViewModelProvider.Factory> {
            ViewModelFactory(di)
        }
        bindSingleton {
            ConnectionCheckFactory(context = instance())
        }
        bindSingleton { CrashReportFactory() }
        bindSingleton {
            StringProviderFactory(context = instance())
        }
    }
}
