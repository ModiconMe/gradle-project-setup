plugins { java }

java {
    val fileVersion = file(rootDir.path.plus("/jdk-version.txt")).readText().trim()
    toolchain.languageVersion = JavaLanguageVersion.of(fileVersion)
}

tasks.withType<JavaCompile>().configureEach {
    options.apply {
        /*
        By forking memory-intensive compilation into a separate process,
        we minimize garbage collection in the main Gradle process.
        */
        isFork = true
        encoding = "UTF-8"
        compilerArgs.add("-implicit:none")
        compilerArgs.add("-Werror")
        compilerArgs.add("-Xlint:all,-serial")
    }
}

tasks.withType<Javadoc>().configureEach {
    options {
        this as StandardJavadocDocletOptions
        encoding = "UTF-8"
        addStringOption("Xwerror", "-Xdoclint:all,-missing")
    }
}

tasks.named("projectBuild") {
    dependsOn(tasks.withType<Javadoc>())
    dependsOn(tasks.withType<JavaCompile>())
}

tasks.processResources { from(file(rootDir.path.plus("/version.txt"))) }
