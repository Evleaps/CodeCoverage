package code_coverage

import code_coverage.data.ModuleCoverageInfo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.gradle.api.GradleException
import org.gradle.api.Project
import java.io.File

object CoverageVerify {

    private const val FILE_LINK_PREFIX = "file:///"

    private var baselineFile: File? = null
    private var baseline: List<ModuleCoverageInfo> = emptyList()

    fun verify(project: Project) {
        val excludedModules = CoverageUtils.getConfiguration(project).excludedModules
        if (excludedModules.contains(project.path)) {
            return
        }

        val baseline = loadBaselineFile(project)

        val currentCoveragePercent = CoverageUtils.getModuleCoveragePercent(project) ?: 0f
        val baselineCoveragePercent = baseline
            .find { it.moduleName == project.path }
            ?.percentage
            ?: 100f

        assertPercentage(
            project = project,
            newPercent = currentCoveragePercent,
            baselinePercent = baselineCoveragePercent,
        )
    }

    private fun loadBaselineFile(project: Project): List<ModuleCoverageInfo> {
        if (baseline.isNotEmpty()) return baseline
        val baselinePath = CoverageUtils.getConfiguration(project).baselinePath
        val baselineFileName = CoverageUtils.getConfiguration(project).baselineFileName

        baselineFile = File(baselinePath, "$baselineFileName.json")
        if (baselineFile?.exists() != true) {
            System.err.println(
                "Code coverage baseline file wasn't found. " +
                    "Please, execute 'coverageGenerateReport' and then 'coverageBaseline' tasks one by another."
            )
            baselineFile = null
        }

        baseline = Json.decodeFromString<List<ModuleCoverageInfo>>(baselineFile!!.readText(Charsets.UTF_8))
        return baseline
    }

    private fun assertPercentage(project: Project, newPercent: Float, baselinePercent: Float) {
        if (newPercent < baselinePercent) {
            val path = CoverageUtils.getKoverReportFile(project)
            throw GradleException(
                "Code coverage in module: \"${project.path}\" is $newPercent%" +
                    " but should be covered at least on $baselinePercent% according to baseline.\n" +
                    "You can compare your PR files with actual ${project.path} report: $FILE_LINK_PREFIX${path?.path}"
            )
        } else {
            println(
                "Code coverage in module: \"${project.path}\" is $newPercent%," +
                    " minimum coverage is $baselinePercent% according to baseline. Great work!"
            )
        }
    }
}
