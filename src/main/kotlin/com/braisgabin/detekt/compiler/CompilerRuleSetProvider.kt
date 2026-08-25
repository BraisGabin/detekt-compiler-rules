package com.braisgabin.detekt.compiler

import dev.detekt.api.RuleSet
import dev.detekt.api.RuleSetId
import dev.detekt.api.RuleSetProvider

class CompilerRuleSetProvider : RuleSetProvider {
    override val ruleSetId = RuleSetId("compiler")

    override fun instance(): RuleSet = RuleSet(
        ruleSetId,
        listOf(
            ::CompilerDiagnostics,
        )
    )
}
