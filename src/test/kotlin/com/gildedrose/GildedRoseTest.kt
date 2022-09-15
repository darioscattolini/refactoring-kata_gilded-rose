package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `sellIn decreases by one after each update`() {
        val initialSellIn = 5
        val item = Item("foo", initialSellIn, 20)
        val app = GildedRose(arrayOf(item))
        val expectedSellInValues = listOf(5, 4, 3, 2, 1, 0)
        val actualSellInValues = mutableListOf(5)

        while (actualSellInValues.size < expectedSellInValues.size) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualSellInValues.add(storedItem!!.sellIn)
        }

        Assertions.assertIterableEquals(expectedSellInValues, actualSellInValues)
    }

}


