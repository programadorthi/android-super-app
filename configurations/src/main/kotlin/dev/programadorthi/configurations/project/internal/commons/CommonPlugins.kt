package dev.programadorthi.configurations.project.internal.commons

import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.theme.ThemeType
import dev.programadorthi.configurations.project.internal.utils.i
import dev.programadorthi.configurations.project.internal.utils.logger
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal fun Project.applyCommonPlugins() {
    with(pluginManager) {
        logger().i("Setting common plugins to project: $name")
        apply("org.jmailen.kotlinter")
        withPlugin("com.adarshr.test-logger") {
            configureTestLogger(this.id)
        }
    }
}

private fun Project.configureTestLogger(pluginId: String) {
    plugins.withId(pluginId) {
        extensions.getByType<TestLoggerExtension>().apply {
            logger().i("Setting test logger to: $name")
            theme = ThemeType.MOCHA_PARALLEL
        }
    }
}
