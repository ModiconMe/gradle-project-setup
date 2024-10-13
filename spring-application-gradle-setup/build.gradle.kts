plugins {
    id("edu.modiconme.lifecycle-root")
    id("edu.modiconme.format-gradle-root")
    id("edu.modiconme.application")
}

dependencies {
    implementation("edu.modiconme.library1:1.0.0")
    implementation("edu.modiconme.library2:1.0.0")

    implementation(libs.spring.boot.web)
    testImplementation(libs.spring.boot.test)
    intTestImplementation(libs.spring.boot.test)
}
