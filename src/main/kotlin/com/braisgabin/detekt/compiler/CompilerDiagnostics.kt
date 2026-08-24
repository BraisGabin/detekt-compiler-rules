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
import org.jetbrains.kotlin.psi.KtElement

class CompilerDiagnostics(
    config: Config,
) : Rule(config, "Reports the info raised by the compiler"),
    RequiresAnalysisApi {

    private val reportOnSeverity: KaSeverity by config("warning") {
        when (it) {
            "info" -> KaSeverity.INFO
            "warning" -> KaSeverity.WARNING
            "error" -> KaSeverity.ERROR
            else -> error("Unknown severity: $it. The valid values are info, warning and error.")
        }
    }
    private val ignoreDiagnostic: Set<String> by config(emptyList(), List<String>::toSet)

    @OptIn(KaExperimentalApi::class)
    override fun visitKtElement(element: KtElement) {
        analyze(element) {
            element.directDiagnostics(KaDiagnosticCheckerFilter.EXTENDED_AND_COMMON_CHECKERS)
                .filter { it.severity.ordinal <= reportOnSeverity.ordinal }
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
        super.visitElement(element)
    }
}
