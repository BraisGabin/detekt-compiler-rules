import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.21"
}

buildscript {
    dependencies {
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.30.0")
    }
}

apply(plugin="com.vanniktech.maven.publish")

dependencies {
    compileOnly("io.gitlab.arturbosch.detekt:detekt-api:1.23.8")

    testImplementation("io.gitlab.arturbosch.detekt:detekt-test:1.23.8")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
}

kotlin {
    jvmToolchain(8)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    systemProperty("junit.jupiter.testinstance.lifecycle.default", "per_class")
    systemProperty("compile-snippet-tests", project.hasProperty("compile-test-snippets"))
}
