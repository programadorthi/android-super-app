package dev.programadorthi.domain.report

interface CrashReport {
    fun report(message: String, cause: Throwable)
}
