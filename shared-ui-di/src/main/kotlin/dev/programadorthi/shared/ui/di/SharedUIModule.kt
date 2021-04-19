package dev.programadorthi.shared.ui.di

import dev.programadorthi.shared.ui.network.ConnectionCheckFactory
import dev.programadorthi.shared.ui.provider.SharedTextProviderFactory
import dev.programadorthi.shared.ui.report.CrashReportFactory
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object SharedUIModule {
    operator fun invoke() = DI.Module("shared-ui") {
        bindSingleton {
            ConnectionCheckFactory(context = instance())
        }
        bindSingleton { CrashReportFactory() }
        bindProvider {
            SharedTextProviderFactory(context = instance())
        }
    }
}
