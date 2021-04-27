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
    implementation(project(JavaModules.SHARED_DOMAIN_DI))
    implementation(project(JavaModules.SHARED_NETWORK_DI))
    implementation(project(JavaModules.SHARED_RETROFIT_DI))
    implementation(project(LibraryModules.SHARED_DATABASE_DI_ANDROID))
    implementation(project(LibraryModules.Features.NorrisFacts.UI))
    implementation(Dependencies.DI.kodein)
    implementation(Dependencies.DI.kodeinAndroid)
    Dependencies.Android.common.forEach { implementation(it) }
}
