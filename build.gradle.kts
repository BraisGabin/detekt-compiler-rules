plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.tapmoc)
}

dependencies {
    compileOnly(libs.detekt.api)

    testImplementation(libs.detekt.test)
    testImplementation(libs.detekt.test.junit)
    testImplementation(libs.kotest)
    testImplementation(libs.junit)
    testRuntimeOnly(libs.junit.launcher)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    systemProperty("junit.jupiter.testinstance.lifecycle.default", "per_class")
    systemProperty("compile-snippet-tests", project.hasProperty("compile-test-snippets"))
}

mavenPublishing {
    publishToMavenCentral(false)

    coordinates("com.braisgabin.detekt", "kotlin-compiler-wrapper")

    pom {
        name.set("Detekt Compiler Rules")
        description.set("Detekt rules to wrap the Kotlin compiler messages")
        inceptionYear.set("2022")
        url.set("https://github.com/BraisGabin/detekt-compiler-rules")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("BraisGabin")
                name.set("Brais Gabín")
                url.set("https://github.com/BraisGabin")
            }
        }
        scm {
            url.set("https://github.com/BraisGabin/detekt-compiler-rules")
            connection.set("scm:git:git://github.com/BraisGabin/detekt-compiler-rules.git")
            developerConnection.set("scm:git:ssh://git@github.com:BraisGabin/detekt-compiler-rules.git")
        }
    }
}

tapmoc {
    java(8)
}
