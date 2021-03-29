package dev.programadorthi.network.exception

import dev.programadorthi.domain.report.CrashReport

interface NetworkingErrorMapper {
    suspend fun mapper(cause: Throwable): NetworkingError

    companion object Instance {
        fun create(crashReport: CrashReport): NetworkingErrorMapper =
            NetworkingErrorMapperImpl(crashReport)
    }
}