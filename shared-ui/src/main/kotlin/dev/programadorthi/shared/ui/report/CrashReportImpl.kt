package dev.programadorthi.shared.ui.report

import dev.programadorthi.shared.domain.report.CrashReport

// FIXME: maybe this guy should be in a specific report module
internal class CrashReportImpl : CrashReport {
    override fun report(message: String, cause: Throwable) {
        println(
            """
        |message: $message
        |cause: $cause
        """.trimIndent()
        )
    }
}
