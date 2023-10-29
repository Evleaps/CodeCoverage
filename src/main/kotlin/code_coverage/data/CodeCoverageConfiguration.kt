package code_coverage.data

import org.gradle.api.GradleException

class CodeCoverageConfiguration {

    companion object {

        const val TAG = "codeCoveragePlugin"
    }

    /**
     * Path where you can find your baseline
     */
    var baselinePath: String = ""
        get() {
            if (field.isBlank()) throw GradleException("Field baselinePath must be declared in the coverage configuration")
            return field
        }

    /**
     * Baseline file name
     */
    var baselineFileName: String = "CodeCoverageBaseline"
        get() {
            if (field.isBlank()) throw GradleException("Field baselineFileName must be declared in the coverage configuration")
            return field
        }

    /**
     * A flavor postfix must be declared if your app has modules with flavors. It will be used for run kover tasks
     * Example: "CatDebug"
     * Result: will be run "koverHtmlReportCatDebug"
     */
    var flavorTaskPostfix: String = ""
        get() {
            if (field.isBlank() && modulesWithFlavors.isNotEmpty()) {
                throw GradleException("Field flavorTaskPostfix must be declared in the coverage configuration")
            }
            return field
        }

    /**
     * These modules will be excluded from the coverage report.
     */
    var excludedModules = emptySet<String>()

    /**
     * Modules with flavours must be declared here for running kover tasks with a right command.
     */
    var modulesWithFlavors = emptySet<String>()
}
