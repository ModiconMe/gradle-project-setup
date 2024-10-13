import org.gradle.example.spotless.SortDependenciesStep

plugins { id("com.diffplug.spotless") }

spotless {
    kotlinGradle {
        ktfmt().kotlinlangStyle().configure { it.setMaxWidth(500) }
        addStep(SortDependenciesStep.create())
    }
}

tasks.named("projectBuild") { dependsOn(tasks.named("spotlessApply")) }
