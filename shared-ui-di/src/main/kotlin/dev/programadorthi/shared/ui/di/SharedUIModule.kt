package dev.programadorthi.shared.ui.di

import dev.programadorthi.shared.domain.DomainInjectionTags
import dev.programadorthi.shared.ui.network.ConnectionCheckFactory
import dev.programadorthi.shared.ui.report.CrashReportFactory
import dev.programadorthi.shared.ui.resource.StringProvider
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object SharedUIModule {
    operator fun invoke() = DI.Module("shared-ui") {
        bindProvider(DomainInjectionTags.IO_DISPATCHER) { Dispatchers.IO }
        bindSingleton {
            ConnectionCheckFactory(context = instance())
        }
        bindSingleton { CrashReportFactory() }
        bindSingleton {
            StringProvider(context = instance())
        }
    }
}
