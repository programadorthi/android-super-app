import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.theme.ThemeType
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    kotlin("android")
    id("org.jmailen.kotlinter")
    id("com.adarshr.test-logger")
    id("io.gitlab.arturbosch.detekt")
}

configure<BaseExtension> {
    val isApplication = this is AppExtension

    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    buildFeatures.viewBinding = true

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

    buildTypes {
        getByName("debug") {
            isCrunchPngs = false

            // https://stackoverflow.com/a/55745719
            (this@getByName as ExtensionAware).apply {
                extra["alwaysUpdateBuildId"] = false
                extra["enableCrashlytics"] = false
            }
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = isApplication

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
            proguardFiles(*(File("$rootDir/proguard").listFiles() ?: emptyArray()))

            // Will be null if has not a release signing
            signingConfig = signingConfigs.findByName("release")
        }
    }

    flavorDimensions("default")

    productFlavors {
        create("dev") {
            dimension = "default"
            applicationIdSuffix = if (isApplication) ".dev" else null
            resConfigs(
                "en-rUS", // Language and region
                "ldltr", // Layout Direction
                "port", // Screen orientation
                "xxxhdpi" // Screen pixel density (dpi)
            )
        }

        create("prd") {
            dimension = "default"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("LICENSE.txt")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/NOTICE")
        exclude("META-INF/LICENSE")
        exclude("META-INF/main.kotlin_module")
    }

    lintOptions {
        isAbortOnError = false
        isIgnoreWarnings = true
        isQuiet = true
        disable("InvalidPackage", "OldTargetApi")
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    sourceSets {
        map { source ->
            source.java.srcDir("src/${source.name}/kotlin")
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "${JavaVersion.VERSION_1_8}"
    }
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

configure<DetektExtension> {
    config = rootProject.files("detekt-config.yml")
    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("prd")
    ignoredVariants = listOf("prdRelease")
    parallel = true
}
