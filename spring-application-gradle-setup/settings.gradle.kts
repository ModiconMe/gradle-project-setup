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
         id("edu.modiconme.application") version pluginsVersion
         id("edu.modiconme.library") version pluginsVersion
     }
}

dependencyResolutionManagement {
    // Get components from Maven Central
    repositories.mavenCentral()
    repositories.maven("../library-repository")
}

rootProject.name = "spring-application-gradle-setup"
