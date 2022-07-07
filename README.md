# Detekt compiler rules

This rule set wraps the warnings and info messages of the [Kotlin][kotlin] compiler as [detekt][detekt] findings.

## How to use it

Add this to your module gradle configuration:

```kotlin
dependencies {
  detektPlugins("com.github.BraisGabin:detekt-compiler-rules:0.0.1")
}
```

Right now the project is on [jitpack]. So you need to have that repo configured too.

[jitpack]: https://jitpack.io/#BraisGabin/detekt-junit-rules
[detekt]: https://detekt.github.io/
[kotlin]: https://kotlinlang.org/
