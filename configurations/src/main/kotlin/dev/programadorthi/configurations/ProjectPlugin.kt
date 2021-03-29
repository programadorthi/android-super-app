package dev.programadorthi.configurations

import dev.programadorthi.configurations.project.internal.commons.applyCommonPlugins
import dev.programadorthi.configurations.project.internal.commons.configureCompatibilityAndJVMTarget
import dev.programadorthi.configurations.project.internal.configureAndroidApplication
import dev.programadorthi.configurations.project.internal.configureAndroidLibrary
import dev.programadorthi.configurations.project.internal.utils.i
import dev.programadorthi.configurations.project.internal.utils.logger
import org.gradle.api.Plugin
import org.gradle.api.Project
import kotlin.system.measureTimeMillis

class ProjectPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.subprojects {
            logger().i("Configuring: $name")
            val spent = measureTimeMillis {
                applyCommonPlugins()
                configureCompatibilityAndJVMTarget()
                configureAndroidApplication()
                configureAndroidLibrary()
            }
            logger().i("$name Configurations done in: $spent milliseconds")
        }
    }
}