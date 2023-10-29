package com.roman_aimaletdinov.analytics

import kotlin.random.Random

class CoveredAnalyticsClass {

    fun getRandomInt(): Int {
        return Random.nextInt()
    }

    fun untestedFoo(): Float {
        return Random.nextFloat()
    }
}