# Detekt compiler rules

This rule set wraps the warnings and info messages of the [Kotlin][kotlin] compiler as [detekt][detekt] findings.

## How to use it

Add this to your module gradle configuration:

```kotlin
dependencies {
  detektPlugins("com.braisgabin.detekt:kotlin-compiler-wrapper:0.0.3")
}
```

## Configuration

If you use detekt `1.21.0` or later all works out of the box. If you don't you should copy the content of [this file][config.yml]
inside your detekt configuration to make able to run the rules.

[config.yml]: https://github.com/BraisGabin/detekt-compiler-rules/blob/main/src/main/resources/config/config.yml
[detekt]: https://detekt.github.io/
[kotlin]: https://kotlinlang.org/
