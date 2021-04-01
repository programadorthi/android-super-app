plugins {
    kotlin("jvm")
    id("super-module")
}

dependencies {
    api(project(":applications:norris-facts:domain-impl"))
}
