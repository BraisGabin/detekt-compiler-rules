# Detekt compiler rules

This rule set reports [Kotlin][kotlin] compiler diagnostics as [detekt][detekt] findings.

## How to use it

Add this to your `build.gradle`:

```kotlin
dependencies {
  detektPlugins("com.braisgabin.detekt:kotlin-compiler-wrapper:0.1.1")
}
```

### Detekt 1 support

If you want to use this plugin with detekt 1 use version `0.0.4`.

[detekt]: https://detekt.dev/
[kotlin]: https://kotlinlang.org/
