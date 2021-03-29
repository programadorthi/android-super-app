package dev.programadorthi.domain.report

interface CrashReport {
    suspend fun report(message: String, cause: Throwable)
}
