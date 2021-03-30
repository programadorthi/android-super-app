package dev.programadorthi.shared.network.fake

import dev.programadorthi.shared.domain.report.CrashReport

class CrashReportFake : CrashReport {
    var message: String? = null
        private set

    var cause: Throwable? = null
        private set

    override fun report(message: String, cause: Throwable) {
        this.message = message
        this.cause = cause
    }
}
