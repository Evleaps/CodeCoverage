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

# Licence
Copyright (c) 2023 Global, LLD

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
```
http://www.apache.org/licenses/LICENSE-2.
```
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

# Disclaimer
All information and source code are provided AS-IS, without express or implied warranties. Use of the source code or parts of it is at your sole discretion and risk. Global LLC takes reasonable measures to ensure the relevance of the information posted in this repository, but it does not assume responsibility for maintaining or updating this repository or its parts outside the framework established by the company independently and without notifying third parties.
