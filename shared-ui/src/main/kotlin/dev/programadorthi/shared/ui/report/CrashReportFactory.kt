package dev.programadorthi.shared.ui.report

import dev.programadorthi.shared.domain.report.CrashReport

object CrashReportFactory {
    operator fun invoke(): CrashReport = CrashReportImpl()
}
