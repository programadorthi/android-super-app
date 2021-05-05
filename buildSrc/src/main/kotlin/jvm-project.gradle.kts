import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.theme.ThemeType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    kotlin("jvm")
    id("org.jmailen.kotlinter")
    id("com.adarshr.test-logger")
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "${JavaVersion.VERSION_1_8}"
    }
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

tasks {
    "lintKotlinMain"(LintTask::class) {
        exclude {
            // Ignoring build at all
            it.file.path.startsWith(buildDir.path)
        }
    }
}

configure<TestLoggerExtension> {
    theme = ThemeType.MOCHA_PARALLEL
}
