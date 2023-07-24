import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0"
}

buildscript {
    dependencies {
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.25.3")
    }
}

apply(plugin="com.vanniktech.maven.publish")

dependencies {
    compileOnly("io.gitlab.arturbosch.detekt:detekt-api:1.23.0")

    testImplementation("io.gitlab.arturbosch.detekt:detekt-test:1.23.0")
    testImplementation("io.kotest:kotest-assertions-core:5.6.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

kotlin {
    jvmToolchain(8)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    systemProperty("junit.jupiter.testinstance.lifecycle.default", "per_class")
    systemProperty("compile-snippet-tests", project.hasProperty("compile-test-snippets"))
}
