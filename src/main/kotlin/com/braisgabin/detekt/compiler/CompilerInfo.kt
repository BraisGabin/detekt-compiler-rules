package com.braisgabin.detekt.compiler

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.diagnostics.Severity.INFO
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext

class CompilerInfo(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        Severity.CodeSmell,
        "Reports the info raised by the compiler",
        Debt.FIVE_MINS,
    )

    override fun visitKtFile(file: KtFile) {
        if (bindingContext == BindingContext.EMPTY) return

        bindingContext.diagnostics
            .filterBySeverityAndFile(INFO, file)
            .forEach { diagnostic ->
                report(
                    CodeSmell(
                        issue,
                        Entity.from(diagnostic.psiElement),
                        "Kotlin compiler info: ${diagnostic.factoryName}",
                    )
                )
            }
    }
}
