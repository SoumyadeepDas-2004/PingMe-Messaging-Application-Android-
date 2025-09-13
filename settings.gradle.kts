// Where Gradle should look for plugins (like Maven Central, Google, Gradle Plugin Portal) while downloading dependencies(plugin)
pluginManagement {
    repositories {
        // Google Maven Repository
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral() // Maven Central Repository
        gradlePluginPortal() // Gradle Plugin Portal(Used to download Gradle plugins)
    }
}
// this block means All dependencies in this project must be downloaded only from Google’s Maven and Maven Central
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google() //  Android libraries like androidx, material
        mavenCentral() // Java/Kotlin/other dependencies
    }
}

rootProject.name = "PingMe" // name of the whole project(shown in android studio)
include(":app") // tells Gradle to include a subproject/module named app
 