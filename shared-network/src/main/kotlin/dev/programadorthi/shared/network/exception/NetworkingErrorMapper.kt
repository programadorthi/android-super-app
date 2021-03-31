package dev.programadorthi.shared.network.exception

import dev.programadorthi.shared.domain.report.CrashReport

interface NetworkingErrorMapper {
    suspend fun mapper(cause: Throwable): NetworkingError

    companion object Instance {
        operator fun invoke(crashReport: CrashReport): NetworkingErrorMapper =
            NetworkingErrorMapperImpl(crashReport)
    }
}
