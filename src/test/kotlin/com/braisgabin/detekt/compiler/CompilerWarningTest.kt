package com.braisgabin.detekt.compiler

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.rules.KotlinCoreEnvironmentTest
import io.gitlab.arturbosch.detekt.test.compileAndLintWithContext
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.junit.jupiter.api.Test

@KotlinCoreEnvironmentTest
internal class CompilerWarningTest(private val env: KotlinCoreEnvironment) {

    @Test
    fun `reports deprecation warning`() {
        val code = """
            @Deprecated("")
            fun a() {}
            
            fun b() { a() }
        """
        val findings = CompilerWarning(Config.empty).compileAndLintWithContext(env, code)
        findings shouldHaveSize 1
        findings[0].message shouldBeEqualComparingTo "Kotlin compiler warning: DEPRECATION"
    }

    @Test
    fun `doesn't report any warning`() {
        val code = """
            fun a() {}
            
            fun b() { a() }
        """
        val findings = CompilerWarning(Config.empty).compileAndLintWithContext(env, code)
        findings shouldHaveSize 0
    }
}
