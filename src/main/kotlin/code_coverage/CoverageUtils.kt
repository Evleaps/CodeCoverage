package code_coverage

import code_coverage.data.CodeCoverageConfiguration
import org.gradle.api.Project
import java.io.File

internal object CoverageUtils {

    fun getKoverReportFile(project: Project): File? {
        val flavorPostfix = getFlavour(project)
        val reportFile = File("${project.projectDir}/build/reports/kover/html$flavorPostfix/index.html")
        if (!reportFile.exists()) {
            System.err.println("In module: $project no coverage report was found.")
            return null
        }

        return reportFile
    }

    fun getModuleCoveragePercent(project: Project): Float? {
        val reportFile = getKoverReportFile(project) ?: return null

        return reportFile
            .readText()
            .substringAfter("<span class=\"percent\">")
            .substringBefore("%")
            .trim()
            .toFloatOrNull() ?: 100f
    }

    fun getFlavour(project: Project): String {
        val isAndroidModule = project.hasProperty("android")
        val modulesWithFlavors = getConfiguration(project).modulesWithFlavors
        val flavorPostfix = getConfiguration(project).flavorTaskPostfix
        val hasFavours = modulesWithFlavors.contains(project.path)
        return when {
            hasFavours -> flavorPostfix
            isAndroidModule -> "Debug"
            else -> ""
        }
    }

    fun getConfiguration(project: Project): CodeCoverageConfiguration {
        return requireNotNull(
            value = project.rootProject.extensions.findByName(CodeCoverageConfiguration.TAG),
            lazyMessage = { "Unable to find ${CodeCoverageConfiguration.TAG} in ${project.rootProject}" }
        ) as CodeCoverageConfiguration
    }
}
