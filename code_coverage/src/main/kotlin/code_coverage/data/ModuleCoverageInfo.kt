package code_coverage.data

import kotlinx.serialization.*

@Serializable
data class ModuleCoverageInfo(
    val moduleName: String,
    val percentage: Float
)
