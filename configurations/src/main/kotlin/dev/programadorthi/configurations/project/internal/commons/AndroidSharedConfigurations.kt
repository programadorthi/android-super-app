package dev.programadorthi.configurations.project.internal.commons

import com.android.build.gradle.BaseExtension
import dev.programadorthi.configurations.project.Definitions
import dev.programadorthi.configurations.project.internal.utils.i
import dev.programadorthi.configurations.project.internal.utils.logger
import dev.programadorthi.configurations.project.internal.utils.mapSourceSets
import org.gradle.api.JavaVersion

internal fun BaseExtension.configureCommonAndroidConfigs() {
    configureDefaultConfig()
    configureFlavorDimensions()
    configurePackagingOptions()
    configureCompileOptions()
    configureLintOptions()
    configureTestOptions()
    mapSourceSets()
}

private fun BaseExtension.configureDefaultConfig() {
    logger().i(">>>> Applying default config to: $this")

    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        versionCode = 1
        versionName = "1.0.0"
        minSdkVersion(23)
        targetSdkVersion(30)
        vectorDrawables {
            useSupportLibrary = true
            setGeneratedDensities(emptyList())
        }
    }
}

private fun BaseExtension.configureFlavorDimensions() {
    flavorDimensions(Definitions.DEFAULT_DIMENSION)
}

private fun BaseExtension.configurePackagingOptions() {
    logger().i(">>>> Applying packaging options to: $this")

    packagingOptions {
        exclude("LICENSE.txt")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/NOTICE")
        exclude("META-INF/LICENSE")
        exclude("META-INF/main.kotlin_module")
    }
}

private fun BaseExtension.configureCompileOptions() {
    logger().i(">>>> Applying compile options to: $this")

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

private fun BaseExtension.configureLintOptions() {
    logger().i(">>>> Applying lint options to: $this")

    lintOptions {
        isAbortOnError = false
        isIgnoreWarnings = true
        isQuiet = true
        disable("InvalidPackage", "OldTargetApi")
    }
}

private fun BaseExtension.configureTestOptions() {
    logger().i(">>>> Applying test options to: $this")

    testOptions {
        unitTests.apply {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}
