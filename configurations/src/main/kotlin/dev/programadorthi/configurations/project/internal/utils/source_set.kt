package dev.programadorthi.configurations.project.internal.utils

import com.android.build.gradle.BaseExtension
import org.gradle.api.tasks.SourceSetContainer

internal fun BaseExtension.mapSourceSets() {
    logger().i(">>>> Mapping android source sets to: $this")
    sourceSets {
        map { source ->
            val dir = source.name.asKotlinDir()
            logger().i(">>>> $source kotlin dir: $dir")
            source.java.srcDir(dir)
        }
    }
}

internal fun SourceSetContainer.mapSourceSets() =
    forEach { source ->
        source.java.srcDir(source.name.asKotlinDir())
    }

private fun String.asKotlinDir() = "src/$this/kotlin"
