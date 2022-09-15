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
        val safeInitialSellIn = expectedQualityValues.size + 1
        val item = Item("foo", safeInitialSellIn, expectedQualityValues.first())
        val app = GildedRose(arrayOf(item))

        while (actualQualityValues.size < expectedQualityValues.size) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualQualityValues.add(storedItem!!.quality)
        }

        Assertions.assertIterableEquals(expectedQualityValues, actualQualityValues)
    }

    @Test
    fun `quality of item cannot decrease to less than 0`() {
        val item = Item("foo", 10, 0)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        val storedItem = app.items.find { it.name === item.name }

        Assertions.assertEquals(storedItem!!.quality, 0)
    }

    @Test
    fun `quality of regular item decreases by two after sellIn date reaches 0`() {
        val expectedQualityValues = listOf(7, 5, 3, 1, 0, 0)
        val actualQualityValues = mutableListOf(expectedQualityValues.first())
        val item = Item("foo", 0, expectedQualityValues.first())
        val app = GildedRose(arrayOf(item))

        while (actualQualityValues.size < expectedQualityValues.size) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualQualityValues.add(storedItem!!.quality)
        }

        Assertions.assertIterableEquals(expectedQualityValues, actualQualityValues)
    }

    @Test
    fun `quality of Aged Brie increases by one after each update`() {
        val expectedQualityValues = listOf(5, 6, 7, 8, 9, 10)
        val actualQualityValues = mutableListOf(expectedQualityValues.first())
        val safeInitialSellIn = expectedQualityValues.size + 1
        val item = Item("Aged Brie", safeInitialSellIn, expectedQualityValues.first())
        val app = GildedRose(arrayOf(item))

        while (actualQualityValues.size < expectedQualityValues.size) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualQualityValues.add(storedItem!!.quality)
        }

        Assertions.assertIterableEquals(expectedQualityValues, actualQualityValues)
    }

    @Test
    fun `quality of item cannot increase to more than 50`() {
        val item = Item("Aged Brie", 10, 50)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        val storedItem = app.items.find { it.name === item.name }

        Assertions.assertEquals(storedItem!!.quality, 50)
    }
}

/*
    Tests a añadir:
    - Quality no se puede inicializar en negativo
    - Quality no se puede inicializar en > 50
 */
