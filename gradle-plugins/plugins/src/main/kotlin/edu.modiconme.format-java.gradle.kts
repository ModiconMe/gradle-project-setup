plugins {
    java
    id("com.diffplug.spotless")
}

tasks.named("projectBuild") { dependsOn(tasks.named("spotlessApply")) }

spotless {
    java {
        importOrder()
        removeUnusedImports()
        cleanthat()
        palantirJavaFormat()
        formatAnnotations()
        licenseHeader("""/* SMARTIX (C) 2024 */""")
    }
}
