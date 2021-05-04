apply(from = "../gradle/android-application.gradle")

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    defaultConfig {
        // TODO: should be this fields module scoped?
        buildConfigField("String", "BASE_URL", "\"https://api.chucknorris.io/jokes/\"")
        buildConfigField("String", "DATABASE_NAME", "\"super_app.db\"")
    }
}

dependencies {
    implementation(projects.sharedDomainDi)
    implementation(projects.sharedNetworkDi)
    implementation(projects.sharedRetrofitDi)
    implementation(projects.sharedDatabaseDiAndroid)
    implementation(projects.features.norrisFacts.ui)
    implementation(libs.kodein.di)
    implementation(libs.kodein.android)
    implementation(libs.bundles.android.common)
}
