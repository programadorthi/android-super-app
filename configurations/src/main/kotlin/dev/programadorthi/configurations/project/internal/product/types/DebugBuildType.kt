package dev.programadorthi.configurations.project.internal.product.types

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import dev.programadorthi.configurations.project.Definitions
import dev.programadorthi.configurations.project.internal.utils.i
import dev.programadorthi.configurations.project.internal.utils.logger
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.extra

internal fun BaseExtension.configureDebugBuildType() {
    logger().i("Configuring debug build type to: $this")
    val isApplication = this is AppExtension

    buildTypes {
        getByName(Definitions.BUILD_TYPE_DEBUG) {
            applicationIdSuffix = if (isApplication) ".dev" else null
            isCrunchPngs = false

            // https://stackoverflow.com/a/55745719
            (this@getByName as ExtensionAware).apply {
                extra["alwaysUpdateBuildId"] = false
                extra["enableCrashlytics"] = false
            }

            defaultConfig {
                resConfigs("xxxhdpi")
            }
        }
    }
}