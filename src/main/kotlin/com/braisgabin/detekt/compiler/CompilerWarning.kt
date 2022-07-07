package com.braisgabin.detekt.compiler

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.diagnostics.Severity.WARNING
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext

class CompilerWarning(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        Severity.CodeSmell,
        "Reports the warnings raised by the compiler",
        Debt.FIVE_MINS,
    )

    override fun visitKtFile(file: KtFile) {
        if (bindingContext == BindingContext.EMPTY) return

        bindingContext.diagnostics
            .filter { it.severity == WARNING }
            .forEach { diagnostic ->
                report(
                    CodeSmell(
                        issue,
                        Entity.from(diagnostic.psiElement),
                        "Kotlin compiler warning: ${diagnostic.factoryName}",
                    )
                )
            }
    }
}
