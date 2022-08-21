# Detekt compiler rules

This rule set wraps the warnings and info messages of the [Kotlin][kotlin] compiler as [detekt][detekt] findings.

## How to use it

Add this to your module gradle configuration:

```kotlin
dependencies {
  detektPlugins("com.braisgabin.detekt:kotlin-compiler-wrapper:0.0.1")
}
```

[detekt]: https://detekt.github.io/
[kotlin]: https://kotlinlang.org/
