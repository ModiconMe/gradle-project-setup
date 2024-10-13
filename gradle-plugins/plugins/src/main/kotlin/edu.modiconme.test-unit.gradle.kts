import gradle.kotlin.dsl.accessors._8c47cae829ea3d03260d5ff13fb2398e.testing
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    base
    jacoco
    id("test-report-aggregation")
    id("jacoco-report-aggregation")
}

@Suppress("UnstableApiUsage")
testing.suites.withType<JvmTestSuite> {
    useJUnitJupiter()
    targets.all {
        testTask {
            testLogging {
                showStackTraces = true
                showStandardStreams = true
                exceptionFormat = TestExceptionFormat.FULL
                events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.STANDARD_ERROR)
            }
            systemProperties["file.encoding"] = "UTF-8"
            maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
        }
    }
}

tasks.withType<JacocoReport> { executionData.from.add(file("build/jacoco/test.exec")) }

tasks.named("check") {
    finalizedBy(tasks.named("testCodeCoverageReport"))
    finalizedBy(tasks.named("testAggregateTestReport"))
    //    dependsOn(tasks.named("jacocoTestReport"))
}

tasks.named("projectBuild") {
    dependsOn(tasks.named("check"))
} /*

  tasks.withType<Test> {
      useJUnitPlatform()
      testLogging {
          showStackTraces = true
          showStandardStreams = true
          exceptionFormat = TestExceptionFormat.FULL
          events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.STANDARD_ERROR)
      }

      systemProperties["file.encoding"] = "UTF-8"

      maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
      */
/*forkEvery = 100*/
/*


*/
/*
systemProperties["junit.jupiter.execution.parallel.enabled"] = false
systemProperties["junit.jupiter.execution.parallel.mode.default"] = "concurrent"
systemProperties["junit.jupiter.execution.parallel.mode.classes.default"] = "concurrent"
systemProperties["junit.jupiter.execution.parallel.config.strategy"] = "fixed"
systemProperties["junit.jupiter.execution.parallel.config.fixed.parallelism"] = 4
*/
/*

}
*/

/*testing.suites.withType<JvmTestSuite> {
    // remove automatically added compile time dependencies, as we define them explicitly
    configurations.getByName(sources.implementationConfigurationName) {
        withDependencies { removeIf { it.group == "org.junit.jupiter" && it.name == "junit-jupiter" } }
    }
    // Configure common test runtime dependencies for *all* projects
    dependencies {
        runtimeOnly("org.junit.jupiter:junit-jupiter-engine")
        runtimeOnly("org.slf4j:slf4j-simple")
    }
}*/
