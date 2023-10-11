# CodeCoverage
The code coverage plugin [based on Kotlin Kover from JetBrains](https://github.com/Kotlin/kotlinx-kover). This plugin adds a baseline support and the ability to configure your CI.

# Description
If you going to support code coverage check on your CI you can use this plugin.
This plugin adds new terminal commands to your project:

- `coverageGenerateReport` - this task will execute a test and create a coverage report.
  You can find them here: `build/reports/kover/htmlDebug/index.html`
- `coverage` - this task will execute tests, generate a coverage report, and
  verify that a module's coverage percent isn't less than a baseline's value.
- `coverageBaseline` - updating baseline to new values. Note that you must execute `coverageGenerateReport` before.

# Configuration
```groovy
codeCoveragePlugin {
    // These modules will be excluded from the coverage report.
    excludedModules = [":design_system"]

    // A flavor postfix must be declared. It will be used for run kover tasks
    // Example: "HuaweiDebug"
    // Result: will be run "koverHtmlReportHuaweiDebug"
    flavorTaskPostfix = "HuaweiDebug"
    // Modules with flavors must be declared here for running kover tasks with the right command.
    modulesWithFlavors = [":app"]

    // Baseline file name
    baselineFileName = "code_coverage_baseline"
    // Path where you can find your baseline
    baselinePath = "${project.rootDir}/tools/test-coverage/report/"
}
```