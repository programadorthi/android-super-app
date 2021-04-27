import dev.programadorthi.dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("super-module")
}

dependencies {
    api(project(JavaModules.SHARED_DATABASE_DI))
    implementation(Dependencies.Android.sqldelightDriver)
    implementation(Dependencies.DI.kodein)
}
