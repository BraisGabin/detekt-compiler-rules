package com.braisgabin.detekt.compiler

import dev.detekt.api.Config
import dev.detekt.test.TestConfig
import dev.detekt.test.junit.KotlinCoreEnvironmentTest
import dev.detekt.test.lintWithContext
import dev.detekt.test.utils.KotlinEnvironmentContainer
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import org.junit.jupiter.api.Test

@KotlinCoreEnvironmentTest
internal class CompilerDiagnosticsTest(private val env: KotlinEnvironmentContainer) {

    @Test
    fun `reports deprecation warning`() {
        val code = """
            @Deprecated("")
            fun a() {}
            
            fun b() { a() }
        """
        val findings = CompilerDiagnostics(Config.empty).lintWithContext(env, code)
        findings shouldHaveSize 1
        findings[0].message shouldBeEqualComparingTo "Kotlin compiler warning: DEPRECATION"
    }

    @Test
    fun `reports deprecation warning when reportOnSeverity is warning`() {
        val code = """
            @Deprecated("")
            fun a() {}
            
            fun b() { a() }
        """
        val findings = CompilerDiagnostics(TestConfig("reportOnSeverity" to "warning")).lintWithContext(env, code)
        findings shouldHaveSize 1
        findings[0].message shouldBeEqualComparingTo "Kotlin compiler warning: DEPRECATION"
    }

    @Test
    fun `doesn't report deprecation warning when reportOnSeverity is error`() {
        val code = """
            @Deprecated("")
            fun a() {}
            
            fun b() { a() }
        """
        val findings = CompilerDiagnostics(TestConfig("reportOnSeverity" to "error")).lintWithContext(env, code)
        findings shouldHaveSize 0
    }

    @Test
    fun `doesn't report any warning`() {
        val code = """
            fun a() {}
            
            fun b() { a() }
        """
        val findings = CompilerDiagnostics(Config.empty).lintWithContext(env, code)
        findings shouldHaveSize 0
    }
}
