plugins { id("com.diffplug.spotless") }

spotless {
    kotlinGradle {
        ktfmt().kotlinlangStyle().configure { it.setMaxWidth(120) }
        target("gradle/plugins/src/main/**/*.gradle.kts")
    }
}

tasks.named("projectBuild") { dependsOn(tasks.named("spotlessApply")) }
