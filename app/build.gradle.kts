import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("super-module")
}

android {
    defaultConfig {
        // TODO: should be this fields module scoped?
        buildConfigField("String", "BASE_URL", "\"https://api.chucknorris.io/jokes/\"")
        buildConfigField("String", "DATABASE_NAME", "\"super_app.db\"")
    }
}

dependencies {
    implementation(project(":shared-database-di-android"))
    implementation(project(":shared-domain-di"))
    implementation(project(":shared-network-di"))
    implementation(project(":shared-retrofit-di"))
    implementation(project(":features:norris-facts:ui"))
    implementation(Dependencies.DI.kodein)
    implementation(Dependencies.DI.kodeinAndroid)
    Dependencies.Android.common.forEach { implementation(it) }
}
