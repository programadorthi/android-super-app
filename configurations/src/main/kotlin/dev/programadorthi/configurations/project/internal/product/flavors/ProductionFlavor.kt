package dev.programadorthi.configurations.project.internal.product.flavors

import com.android.build.gradle.BaseExtension
import dev.programadorthi.configurations.project.Definitions

internal fun BaseExtension.configureProductionFlavor() {
    productFlavors {
        create(Definitions.FLAVOR_PRODUCTION) {
            dimension = Definitions.DEFAULT_DIMENSION
        }
    }
}