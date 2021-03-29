package dev.programadorthi.configurations.project.internal.product.types

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import dev.programadorthi.configurations.project.Definitions
import dev.programadorthi.configurations.project.internal.utils.i
import dev.programadorthi.configurations.project.internal.utils.logger
import java.io.File

internal fun BaseExtension.configureReleaseBuildType(rootDir: File) {
    logger().i("Configuring release build type to: $this")

    val isApplication = this is AppExtension

    buildTypes {
        getByName(Definitions.BUILD_TYPE_RELEASE) {
            isMinifyEnabled = isApplication
            isShrinkResources = isApplication

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
            proguardFiles(*(File("$rootDir/proguard").listFiles() ?: emptyArray()))

            // Will be null if has not a release signing
            signingConfig = signingConfigs.findByName(Definitions.BUILD_TYPE_RELEASE)
        }
    }
}