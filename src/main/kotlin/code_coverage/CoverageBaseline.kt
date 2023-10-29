package code_coverage

import code_coverage.data.ModuleCoverageInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.gradle.api.Project
import java.io.File

class CoverageBaseline {

    private var baselineFile: File? = null
    private val coverageInfoList = mutableListOf<ModuleCoverageInfo>()

    fun clearBaseline(project: Project) {
        val baselineFile = getOrCreateBaselineFile(project)
        baselineFile.writeText("")
    }

    fun update(project: Project) {
        val isRoot = project.path.isBlank()
        if (isRoot) return

        // prepare baseline lines
        project.subprojects {
            val excludedModules = CoverageUtils.getConfiguration(project).excludedModules
            if (!excludedModules.contains(this.path)) {
                val coverageInfo = ModuleCoverageInfo(
                    moduleName = this.path,
                    percentage = CoverageUtils.getModuleCoveragePercent(this) ?: 0f
                )
                coverageInfoList.add(coverageInfo)
            }
        }

        val baselineFile = getOrCreateBaselineFile(project)
        val json = Json {
            prettyPrint = true
        }
        baselineFile.writeText(json.encodeToString(coverageInfoList))
    }

    private fun getOrCreateBaselineFile(project: Project): File {
        val baselinePath = CoverageUtils.getConfiguration(project).baselinePath
        val baselineFileName = CoverageUtils.getConfiguration(project).baselineFileName

        if (baselineFile == null) {
            try {
                baselineFile = File(baselinePath, "$baselineFileName.json")
                baselineFile?.createNewFile()
            } catch (e: Exception) {
                System.err.println("Couldn't create a baseline file: $e")
            }
        }

        return baselineFile!!
    }
}
