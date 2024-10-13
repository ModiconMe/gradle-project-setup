plugins {
    `kotlin-dsl`
    id("maven-publish")
}

repositories { gradlePluginPortal() }

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:7.0.0.BETA2")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.3.4")
    implementation("io.spring.gradle:dependency-management-plugin:1.1.6")
    implementation("org.jfrog.buildinfo:build-info-extractor-gradle:5.+")
}

group = "edu.modiconme"
version = "1.0"
publishing.repositories.maven("../../gradle-plugins-repository")