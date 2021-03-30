package dev.programadorthi.shared.network.exception

import dev.programadorthi.shared.domain.report.CrashReport
import kotlinx.serialization.SerializationException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class NetworkingErrorMapperImpl(
    private val crashReport: CrashReport
) : NetworkingErrorMapper {
    override suspend fun mapper(cause: Throwable): NetworkingError {
        val error = when (cause) {
            is NetworkingError.EssentialParamMissing -> cause
            is SerializationException -> NetworkingError.InvalidDataFormat
            is SocketTimeoutException -> NetworkingError.ConnectionTimeout
            is UnknownHostException -> NetworkingError.UnknownEndpoint(cause)
            else -> NetworkingError.UnknownNetworkException(cause)
        }

        if (error.needsReport()) {
            crashReport.report(
                message = "Original cause was mapped to $error",
                cause = cause
            )
        }

        return error
    }
}
