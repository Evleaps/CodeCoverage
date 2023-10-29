package com.roman_aimaletdinov.analytics

import kotlin.random.Random

// Note: this class covered by 100% according the class coverage in the kover report.
// If you need calculate coverage by line or method you need configure kover library.
// Also you may contribute to this library as well.
class CoveredAnalyticsClass {

    fun getRandomInt(): Int {
        return Random.nextInt()
    }

    fun untestedFoo(): Float {
        return Random.nextFloat()
    }
}