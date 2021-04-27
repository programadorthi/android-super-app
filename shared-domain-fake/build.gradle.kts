plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(JavaModules.SHARED_DOMAIN))
}
