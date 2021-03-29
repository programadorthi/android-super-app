package dev.programadorthi.configurations.project.internal

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import dev.programadorthi.configurations.project.internal.commons.configureCommonAndroidConfigs
import dev.programadorthi.configurations.project.internal.product.flavors.configureDevelopmentFlavor
import dev.programadorthi.configurations.project.internal.product.flavors.configureProductionFlavor
import dev.programadorthi.configurations.project.internal.product.types.configureDebugBuildType
import dev.programadorthi.configurations.project.internal.product.types.configureReleaseBuildType
import dev.programadorthi.configurations.project.internal.utils.i
import dev.programadorthi.configurations.project.internal.utils.logger
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType

internal fun Project.configureAndroidLibrary() {
    plugins.withType<LibraryPlugin> {
        extensions.getByType<LibraryExtension>().apply {
            logger().i("Setting android library configurations to: $name")

            configureCommonAndroidConfigs()
            configureConsumerProguard()
            configureDebugBuildType()
            configureReleaseBuildType(rootDir)
            configureDevelopmentFlavor()
            configureProductionFlavor()
        }
    }
}

private fun LibraryExtension.configureConsumerProguard() {
    defaultConfig {
        consumerProguardFiles(*(arrayOf("consumer-rules.pro")))
    }
}