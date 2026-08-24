rootProject.name = "detekt-compiler-rules"

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
