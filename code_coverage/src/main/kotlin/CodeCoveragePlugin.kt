@file:Suppress("MissingPackageDeclaration")

import code_coverage.CoverageBaseline
import code_coverage.CoverageExecutor
import code_coverage.CoverageVerify
import code_coverage.data.CodeCoverageConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project

class CodeCoveragePlugin : Plugin<Project> {

    private companion object {

        const val BASELINE_TASK_NAME = "coverageBaseline"
        const val GENERATE_REPORT_TASK_NAME = "coverageGenerateReport"
        const val VERIFY_COVERAGE_TASK_NAME = "coverage"
    }

    override fun apply(project: Project) {
        registerConfiguration(project)
        registerTasks(project)
    }

    private fun registerTasks(project: Project) {
        project.tasks.register(BASELINE_TASK_NAME) {
            doFirst {
                val coverageBaselineTask = CoverageBaseline()
                coverageBaselineTask.clearBaseline(project)
                coverageBaselineTask.update(project)
            }
        }

        project
            .subprojects
            .forEach { module ->
                module.tasks.register(VERIFY_COVERAGE_TASK_NAME) {
                    this.dependsOn(GENERATE_REPORT_TASK_NAME)
                    doLast {
                        CoverageVerify.verify(module)
                    }
                }

                module.tasks.register(GENERATE_REPORT_TASK_NAME) {
                    CoverageExecutor.executeWithHtmlReport(module, this)
                }
            }
    }

    private fun registerConfiguration(project: Project) {
        project.extensions.add(
            CodeCoverageConfiguration.TAG,
            CodeCoverageConfiguration()
        )
    }
}
