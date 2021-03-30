package dev.programadorthi.configurations

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete

class ConfigurationsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            tasks.register("clean", Delete::class.java) {
                delete(rootProject.buildDir)
            }
        }
    }
}
