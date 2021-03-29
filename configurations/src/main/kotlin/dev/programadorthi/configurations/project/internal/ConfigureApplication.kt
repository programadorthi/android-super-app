package dev.programadorthi.configurations.project.internal

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
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

internal fun Project.configureAndroidApplication() {
    plugins.withType<AppPlugin> {
        extensions.getByType<AppExtension>().apply {
            logger().i("Setting android application configurations to: $name")

            configureCommonAndroidConfigs()
            configureDebugBuildType()
            configureReleaseBuildType(rootDir)
            configureDevelopmentFlavor()
            configureProductionFlavor()
        }
    }
}