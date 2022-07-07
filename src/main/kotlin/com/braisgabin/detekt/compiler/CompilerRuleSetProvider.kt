package com.braisgabin.detekt.compiler

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CompilerRuleSetProvider : RuleSetProvider {
    override val ruleSetId: String = "compiler"

    override fun instance(config: Config): RuleSet {
        return RuleSet(
            ruleSetId,
            listOf(
                MyRule(config),
            ),
        )
    }
}
