package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `sellIn decreases by one after each update`() {
        val expectedSellInValues = listOf(3, 2, 1, 0, -1, -2)
        val actualSellInValues = mutableListOf(expectedSellInValues.first())
        val item = Item("foo", expectedSellInValues.first(), 20)
        val app = GildedRose(arrayOf(item))

        while (actualSellInValues.size < expectedSellInValues.size) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualSellInValues.add(storedItem!!.sellIn)
        }

        Assertions.assertIterableEquals(expectedSellInValues, actualSellInValues)
    }

    @Test
    fun `quality of regular item decreases by one after each update`() {
        val expectedQualityValues = listOf(5, 4, 3, 2, 1, 0)
        val actualQualityValues = mutableListOf(expectedQualityValues.first())
        val item = Item("foo", 10, expectedQualityValues.first())
        val app = GildedRose(arrayOf(item))

        while (actualQualityValues.size < expectedQualityValues.size) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualQualityValues.add(storedItem!!.quality)
        }

        Assertions.assertIterableEquals(expectedQualityValues, actualQualityValues)
    }

}


