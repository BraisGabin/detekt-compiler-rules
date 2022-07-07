package com.braisgabin.detekt.compiler

import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.diagnostics.Severity
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.diagnostics.Diagnostics

fun Diagnostics.filterBySeverityAndFile(
    severity: Severity,
    file: KtFile,
    ignoreDiagnostic: List<String>,
): Sequence<Diagnostic> {
    return asSequence()
        .filter { it.severity == severity }
        .filter { it.psiFile.containingFile === file }
        .filter { it.factoryName !in ignoreDiagnostic }
}
