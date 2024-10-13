plugins {
    java
    `maven-publish`
    //    id ("com.jfrog.artifactory")
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            afterEvaluate { artifactId = tasks.jar.get().archiveBaseName.get() }
        }
    }
    repositories {
        maven(providers.environmentVariable("PUBLISHING_REPOSITORY_URL").getOrElse("../../library-repository")) {
            if (url.scheme == "https") {
                credentials {
                    username = providers.environmentVariable("PUBLISHING_REPOSITORY_USER").get()
                    password = providers.environmentVariable("PUBLISHING_REPOSITORY_PWD").get()
                }
            }
        }
    }
} /*
  artifactory {
      publish {
          contextUrl = providers.gradleProperty("artifactory2_contextUrl").get()
          repository {
              repoKey = "${providers.gradleProperty("project_group").get()}-${providers.gradleProperty("project_type").get()}" +
                      (if ((project.ext["isMaster"] as Boolean)) "-prod" else "-dev")
              username = providers.gradleProperty("artifactory2_user").get()
              password = providers.gradleProperty("artifactory2_password").get()
          }
          defaults {
              publications("mavenJava")
          }
      }
  }*/
