package com.braisgabin.detekt.compiler

import dev.detekt.api.Config
import dev.detekt.api.Entity
import dev.detekt.api.Finding
import dev.detekt.api.RequiresAnalysisApi
import dev.detekt.api.Rule
import dev.detekt.api.config
import org.jetbrains.kotlin.analysis.api.KaExperimentalApi
import org.jetbrains.kotlin.analysis.api.analyze
import org.jetbrains.kotlin.analysis.api.components.KaDiagnosticCheckerFilter
import org.jetbrains.kotlin.analysis.api.diagnostics.KaSeverity
import org.jetbrains.kotlin.psi.KtFile

class CompilerDiagnostics(
    config: Config,
) : Rule(config, "Reports the info raised by the compiler"),
    RequiresAnalysisApi {

    private val reportSeverities: Set<KaSeverity> by config(listOf("warning")) { it.map(String::toSeverity).toSet() }

    private val ignoreDiagnostic: Set<String> by config(listOf("REDUNDANT_VISIBILITY_MODIFIER"), List<String>::toSet)

    @OptIn(KaExperimentalApi::class)
    override fun visit(root: KtFile) {
        analyze(root) {
            root.diagnostics(KaDiagnosticCheckerFilter.EXTENDED_AND_COMMON_CHECKERS)
                .filter { it.severity in reportSeverities }
                .filter { it.factoryName !in ignoreDiagnostic }
                .forEach { diagnostic ->
                    report(
                        Finding(
                            Entity.from(diagnostic.psi),
                            "Kotlin compiler ${diagnostic.severity.toString().lowercase()}: ${diagnostic.factoryName}",
                        )
                    )
                }
        }
    }
}

private fun String.toSeverity() = when (this) {
    "info" -> KaSeverity.INFO
    "warning" -> KaSeverity.WARNING
    "error" -> KaSeverity.ERROR
    else -> error("Unknown severity: $this. The valid values are \"info\", \"warning\" and \"error\".")
}
