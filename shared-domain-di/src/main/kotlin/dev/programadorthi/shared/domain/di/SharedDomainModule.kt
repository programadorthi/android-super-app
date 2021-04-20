package dev.programadorthi.shared.domain.di

import dev.programadorthi.shared.domain.DomainInjectionTags
import dev.programadorthi.shared.domain.exception.NetworkingErrorMapper
import dev.programadorthi.shared.domain.viewmodel.ViewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
        bindProvider(tag = DomainInjectionTags.VIEW_MODEL_SCOPE) {
            val ioDispatcher: CoroutineDispatcher = instance(DomainInjectionTags.IO_DISPATCHER)
            ViewModelScope(coroutineContext = Job() + ioDispatcher)
        }
    }
}
