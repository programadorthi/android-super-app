plugins {
    id("com.android.application")
    id("android-project")
    // Gradle plugins are declared on buildSrc
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    defaultConfig {
        // TODO: should be this fields module scoped?
        buildConfigField("String", "BASE_URL", "\"https://api.chucknorris.io/jokes/\"")
        buildConfigField("String", "DATABASE_NAME", "\"super_app.db\"")
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(projects.sharedDomainDi)
    implementation(projects.sharedNetworkDi)
    implementation(projects.sharedRetrofitDi)
    implementation(projects.sharedDatabaseDiAndroid)
    implementation(projects.features.norrisFacts.ui)
    implementation(libs.hilt.core)
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.serialization)
    implementation(libs.retrofit)
    implementation(libs.bundles.android.common)
    kapt(libs.hilt.compiler)
}
