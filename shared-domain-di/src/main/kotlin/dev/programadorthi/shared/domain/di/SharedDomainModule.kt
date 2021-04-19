package dev.programadorthi.shared.domain.di

import dev.programadorthi.shared.domain.DomainInjectionTags
import dev.programadorthi.shared.domain.exception.NetworkingErrorMapper
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

object SharedDomainModule {
    operator fun invoke() = DI.Module("shared-domain") {
        bindProvider(tag = DomainInjectionTags.IO_DISPATCHER) {
            Dispatchers.IO
        }
        bindProvider {
            NetworkingErrorMapper(
                crashReport = instance()
            )
        }
    }
}
