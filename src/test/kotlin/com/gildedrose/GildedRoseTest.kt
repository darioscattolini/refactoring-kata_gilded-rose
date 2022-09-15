package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
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

}


