package com.roman_aimaletdinov.analytics

import org.junit.Test

class CoveredAnalyticsClassTest {

    private val coveredAnalyticsClass = CoveredAnalyticsClass()

    @Test
    fun `WHEN getRandomInt THEN values aren't equal`() {
        val one = coveredAnalyticsClass.getRandomInt()
        val two = coveredAnalyticsClass.getRandomInt()
        assert(one != two)
    }
}