package dev.programadorthi.shared.network.exception

import dev.programadorthi.shared.domain.report.CrashReport

interface NetworkingErrorMapper {
    suspend fun mapper(cause: Throwable): NetworkingError

    companion object Instance {
        fun create(crashReport: CrashReport): NetworkingErrorMapper =
            NetworkingErrorMapperImpl(crashReport)
    }
}
