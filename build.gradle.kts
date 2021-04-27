// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("configurations")
    id("com.savvasdalkitsis.module-dependency-graph") version "0.9"
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
