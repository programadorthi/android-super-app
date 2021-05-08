plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    val kotlinVersion = "1.4.32"
    implementation(kotlin("gradle-plugin", version = kotlinVersion))
    implementation(kotlin("serialization", version = kotlinVersion))
    implementation("com.android.tools.build:gradle:4.1.3")
    implementation("com.squareup.sqldelight:gradle-plugin:1.4.4")
    implementation("org.jmailen.gradle:kotlinter-gradle:3.4.4")
    implementation("com.adarshr:gradle-test-logger-plugin:3.0.0")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.16.0")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.35")
}
