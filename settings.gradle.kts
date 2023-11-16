pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "My Application"
include(":app")
include(":complexnotify")
include(":calendar")
include(":diagrams")
include(":picasso")
include(":retrofit")
include(":retrfot_task3")
include(":firebase")
include(":forebase2")
include(":firebase_task3")
