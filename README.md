# Detekt compiler rules

This rule set reports [Kotlin][kotlin] compiler diagnostics as [detekt][detekt] findings.

## How to use it

Add this to your `build.gradle`:

```kotlin
dependencies {
  detektPlugins("com.braisgabin.detekt:kotlin-compiler-wrapper:0.1.0")
}
```

[detekt]: https://detekt.dev/
[kotlin]: https://kotlinlang.org/
