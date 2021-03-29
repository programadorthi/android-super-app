package dev.programadorthi.configurations.project.internal.commons

import dev.programadorthi.configurations.project.internal.utils.i
import dev.programadorthi.configurations.project.internal.utils.logger
import dev.programadorthi.configurations.project.internal.utils.mapSourceSets
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureCompatibilityAndJVMTarget() {
    configureCompatibility()
    configureKotlin()
    configureSourceSet()
}

private fun Project.configureCompatibility() {
    plugins.withType<JavaBasePlugin> {
        extensions.getByType<JavaPluginExtension>().apply {
            logger().i("Setting java compatibility to: $name")

            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            logger().i("Setting kotlin jvmTarget to: $name")

            jvmTarget = "${JavaVersion.VERSION_1_8}"
        }
    }
}

private fun Project.configureSourceSet() {
    logger().i("Looking for extension: sourceSets")
    extensions.findByName("sourceSets")?.let { extension ->
        logger().i("Found extension sourceSets: $extension")
        if (extension is SourceSetContainer) {
            extension.mapSourceSets()
        }
    }
}