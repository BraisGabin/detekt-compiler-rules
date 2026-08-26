# Change Log

## Version 0.1.1 *(2026-08-26)*
- Replace `reportOnSeverity` for `reportSeverities`. It receives a list of severities. Allowed values: `info`, `warning` and `error`.

## Version 0.1.0 *(2026-08-25)*
- Remove support for detekt 1 and add it for detekt 2 (alpha6)
- The rules `CompilerInfo` and `CompilerWarning` are replaced by `CompilerDiagnostics`. This rule lets you define reportOnSeverity so you can decide to report only warnings or also info.

## Version 0.0.4 *(2023-01-24)*
- Support Java 8

## Version 0.0.2 *(2022-08-21)*
- Upload to Maven Central

## Version 0.0.1 *(2022-07-07)*
- Initial release
