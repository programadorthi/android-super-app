package dev.programadorthi.configurations.project.internal.product.flavors

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import dev.programadorthi.configurations.project.Definitions

internal fun BaseExtension.configureDevelopmentFlavor() {
    val isApplication = this is AppExtension

    productFlavors {
        create(Definitions.FLAVOR_DEVELOPMENT) {
            dimension = Definitions.DEFAULT_DIMENSION
            applicationIdSuffix = if (isApplication) ".dev" else null
            resConfigs(
                "en-rUS", // Language and region
                "ldltr", // Layout Direction
                "port", // Screen orientation
                "xxxhdpi" // Screen pixel density (dpi)
            )
        }
    }
}
