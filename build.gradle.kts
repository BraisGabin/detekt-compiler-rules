plugins {
    kotlin("jvm") version "2.2.21"
    id("com.vanniktech.maven.publish") version "0.37.0"
}

dependencies {
    compileOnly("io.gitlab.arturbosch.detekt:detekt-api:1.23.8")

    testImplementation("io.gitlab.arturbosch.detekt:detekt-test:1.23.8")
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    jvmToolchain(8)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    systemProperty("junit.jupiter.testinstance.lifecycle.default", "per_class")
    systemProperty("compile-snippet-tests", project.hasProperty("compile-test-snippets"))
}

mavenPublishing {
    publishToMavenCentral(false)

    coordinates("com.braisgabin.detekt", "kotlin-compiler-wrapper", "0.0.4")

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
