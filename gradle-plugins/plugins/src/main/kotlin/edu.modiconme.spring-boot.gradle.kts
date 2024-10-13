plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

tasks.named<Jar>("jar") { enabled = false }
