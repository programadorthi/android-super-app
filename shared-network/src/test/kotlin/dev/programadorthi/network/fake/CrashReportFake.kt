package dev.programadorthi.network.fake

import dev.programadorthi.domain.report.CrashReport

class CrashReportFake : CrashReport {
    var message: String? = null
        private set

    var cause: Throwable? = null
        private set

    override suspend fun report(message: String, cause: Throwable) {
        this.message = message
        this.cause = cause
    }
}
