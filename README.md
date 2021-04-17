# android-super-app
[![Actions Status](https://github.com/programadorthi/android-super-app/workflows/main/badge.svg)](https://github.com/programadorthi/android-super-app/actions)
[![Kotlin](https://img.shields.io/badge/kotlin-1.4.30-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Coroutines](https://img.shields.io/badge/Kotlin-Coroutines-orange)](https://kotlinlang.org/docs/coroutines-guide.html)
<br/>
<br/>
An Android app with many challenge modules and SOLID at all.

## Features
* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) with Flow (State Flow)
* [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)
* [SQLDelight](https://github.com/cashapp/sqldelight) for local persistence
* [Kodein](https://github.com/Kodein-Framework/Kodein-DI) as dependency injection framework
* [Retrofit](https://github.com/square/retrofit) a type-safe HTTP client for Android and the JVM
* Clean Architecture with MVVM
* Kotlin Gradle DSL
* Build system customized combining Kotlin DSL and Gradle [Composing Builds](https://docs.gradle.org/current/userguide/composite_builds.html)
  
## Build system  
The project is using two gradle [includeBuild](https://docs.gradle.org/current/userguide/composite_builds.html) projects to manage dependencies and projects setup instead of [buildSrc](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources). From gradle docs:  
> A change in buildSrc causes the whole project to become out-of-date. Thus, when making small incremental changes, the --no-rebuild command-line option is often helpful to get faster feedback. Remember to run a full build regularly or at least when you’re done, though.
- **dependencies**: As name says the project manage all dependencies definitions to set as implementation in other projects. Build plugin dependencies are here too.
- **configurations**: It has plugins and configurations that must be applied to other projects. Apply build plugins, apply common plugins to any project type, setup JVM target and compatibilities and Android application or library default configs.

## Project structures
<img src="module_graph.png" />
<br/>

- **shared-module**: are modules that are base project to other feature concrete layers. 
- **domain**: are modules with abstraction and contracts to be implemented. 
- **module-di**: are modules that contains dependency injection setup only.
- **module-fake**: are modules with fake implementations to be used in test modules.
- **module-impl**: are modules with implementations to abstractions defined by domain.
- **module-test**: are modules to do unit tests with JUnit or Robolectric.
- **module-test-android**: are modules to do UI tests with Espresso.
- **ui**: are modules with platform specific implementations. In our case Android code only.

## Prerequisite
To build this project, you require:
- Android Studio 4.1.3
- Gradle 6.8.3

## Credits
- [Reaktive team](https://github.com/badoo/Reaktive) and they build system structure.
- [@vRallev](https://github.com/vRallev) for [Android at Scale @Square](https://www.droidcon.com/media-detail?video=380843878)
- All team/people behind frameworks in the Features section above
