package code_coverage

import org.gradle.api.Project
import org.gradle.api.Task

object CoverageExecutor {

    fun executeWithHtmlReport(project: Project, task: Task) {
        val excludedModules = CoverageUtils.getConfiguration(project).excludedModules
        if (!excludedModules.contains(project.path)) {
            val flavourPostfix = CoverageUtils.getFlavour(project)
            task.dependsOn("koverHtmlReport$flavourPostfix")
        }
    }
}
