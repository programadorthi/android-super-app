package dev.programadorthi.configurations.project.internal.commons

import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.theme.ThemeType
import dev.programadorthi.configurations.project.internal.utils.i
import dev.programadorthi.configurations.project.internal.utils.logger
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.invoke
import org.jmailen.gradle.kotlinter.tasks.LintTask

internal fun Project.applyCommonPlugins() {
    apply(plugin = "org.jmailen.kotlinter")
    apply(plugin = "com.adarshr.test-logger")
    configureKotlinter()
    configureTestLogger()
}

private fun Project.configureKotlinter() {
    tasks {
        "lintKotlinMain"(LintTask::class) {
            exclude {
                // Ignoring build dir because SQLDelight files are generated on it
                it.file.path.startsWith(buildDir.path)
            }
        }
    }
}

private fun Project.configureTestLogger() {
    extensions.getByType<TestLoggerExtension>().apply {
        logger().i("Setting test logger to: $name")
        theme = ThemeType.MOCHA_PARALLEL
    }
}
