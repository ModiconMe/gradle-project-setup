plugins { java }

// val intTestSourceSet =
//    sourceSets.register("intTest") {
//        java {
//            compileClasspath += sourceSets.main.get().output
//            runtimeClasspath += sourceSets.main.get().output
//        }
//    }
//
// val intTestCompileOnly: Configuration by
// configurations.getting { extendsFrom(configurations.compileOnly.get(),
// configurations.testCompileOnly.get()) }
//
// val intTestRuntimeOnly: Configuration by
// configurations.getting { extendsFrom(configurations.runtimeOnly.get(),
// configurations.testRuntimeOnly.get()) }
//
// val intTestImplementation: Configuration by
// configurations.getting { extendsFrom(intTestCompileOnly, intTestRuntimeOnly) }
//
// val intTestTask =
//    task<Test>("intTest") {
//        description = "Runs integration tests."
//        group = "verification"
//
//        testClassesDirs = intTestSourceSet.get().output.classesDirs
//        classpath = intTestSourceSet.get().runtimeClasspath
//
//        shouldRunAfter(JavaPlugin.TEST_TASK_NAME)
//    }
//
// tasks.named("check") { dependsOn(intTestTask) }

@Suppress("UnstableApiUsage")
testing.suites.register<JvmTestSuite>("intTest") {
    dependencies { implementation(project()) }
    testType = TestSuiteType.INTEGRATION_TEST
    targets.all {
        testTask.configure { shouldRunAfter(tasks.named<Test>("test")) }
        tasks.check { dependsOn(testTask) }
    }
    sources {
        java {
            compileClasspath += sourceSets.main.get().output
            runtimeClasspath += sourceSets.main.get().output
        }
    }
}

tasks.withType<JacocoReport> { executionData.from.add(file("build/jacoco/intTest.exec")) }

// Integrate INTEGRATION_TEST results into the aggregated UNIT_TEST coverage results
@Suppress("UnstableApiUsage")
tasks.named<JacocoReportBase>("testCodeCoverageReport") {
    executionData.from(
        configurations
            .named<Configuration>("aggregateCodeCoverageReportResults")
            .get()
            .incoming
            .artifactView {
                lenient(true)
                withVariantReselection()
                attributes {
                    attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.VERIFICATION))
                    attribute(
                        VerificationType.VERIFICATION_TYPE_ATTRIBUTE,
                        objects.named(VerificationType.JACOCO_RESULTS),
                    )
                    attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, ArtifactTypeDefinition.BINARY_DATA_TYPE)
                }
            }
            .files
    )
}

// Integrate INTEGRATION_TEST results into the aggregated UNIT_TEST test results
@Suppress("UnstableApiUsage")
tasks.named<TestReport>("testAggregateTestReport") {
    testResults.from(
        configurations
            .named<Configuration>("aggregateTestReportResults")
            .get()
            .incoming
            .artifactView {
                lenient(true)
                withVariantReselection()
                attributes {
                    attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.VERIFICATION))
                    attribute(
                        VerificationType.VERIFICATION_TYPE_ATTRIBUTE,
                        objects.named(VerificationType.TEST_RESULTS),
                    )
                }
            }
            .files
    )
}
