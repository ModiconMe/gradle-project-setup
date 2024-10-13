pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    // Подгружаем precompiled плагины из папки
    //includeBuild("gradle/plugins")

    // Подгружаем плагины из репозитория
     repositories.maven("../gradle-plugins-repository")
     plugins {
         val pluginsVersion = "1.0"
         id("edu.modiconme.lifecycle-root") version pluginsVersion
         id("edu.modiconme.format-gradle-root") version pluginsVersion
         id("edu.modiconme.library") version pluginsVersion
     }
}

dependencyResolutionManagement {
    // Get components from Maven Central
    repositories.mavenCentral()
}

rootDir.listFiles()?.filter { File(it, "build.gradle.kts").exists() }?.forEach { subproject -> include(subproject.name) }

rootProject.name = "library-gradle-setup"
