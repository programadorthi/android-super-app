package dev.programadorthi.shared.domain.report

interface CrashReport {
    fun report(message: String, cause: Throwable)
}
