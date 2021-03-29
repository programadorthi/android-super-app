package dev.programadorthi.configurations.project.internal.utils

import com.android.build.gradle.BaseExtension
import org.gradle.api.tasks.SourceSetContainer

internal fun BaseExtension.mapSourceSets() {
    logger().i(">>>> Mapping android source sets to: $this")

    sourceSets {
        map { source ->
            val dir = source.addKotlinDir()

            logger().i(">>>> $source kotlin dir: $dir")

            source.java.srcDir(dir)
        }
    }
}

internal fun SourceSetContainer.mapSourceSets() =
    map { source ->
        source.java.srcDir(source.addKotlinDir())
    }

private fun Any.addKotlinDir() = "src/$this/kotlin"