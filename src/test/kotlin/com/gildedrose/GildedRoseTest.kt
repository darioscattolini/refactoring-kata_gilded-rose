package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    private val backstagePassName = "Backstage passes to a TAFKAL80ETC concert"

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
        val item = Item("foo", 10, 1)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
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
        val item = Item("Aged Brie", 10, 49)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.updateQuality()
        val storedItem = app.items.find { it.name === item.name }

        Assertions.assertEquals(storedItem!!.quality, 50)
    }

    @Test
    fun `quality of Aged Brie item increases by two after sellIn date reaches 0`() {
        val expectedQualityValues = listOf(43, 45, 47, 49, 50, 50)
        val actualQualityValues = mutableListOf(expectedQualityValues.first())
        val item = Item("Aged Brie", 0, expectedQualityValues.first())
        val app = GildedRose(arrayOf(item))

        while (actualQualityValues.size < expectedQualityValues.size) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualQualityValues.add(storedItem!!.quality)
        }

        Assertions.assertIterableEquals(expectedQualityValues, actualQualityValues)
    }

    @Test
    fun `sellIn of Sulfuras never changes`() {
        val item = Item("Sulfuras, Hand of Ragnaros", 5, 80)
        val app = GildedRose(arrayOf(item))

        repeat(3) { app.updateQuality() }

        val storedItem = app.items.find { it.name === item.name }
        Assertions.assertEquals(storedItem!!.sellIn, item.sellIn)
    }

    @Test
    fun `quality of Sulfuras never changes (even if above 50)`() {
        val item = Item("Sulfuras, Hand of Ragnaros", 5, 80)
        val app = GildedRose(arrayOf(item))

        repeat(3) { app.updateQuality() }

        val storedItem = app.items.find { it.name === item.name }
        Assertions.assertEquals(storedItem!!.quality, item.quality)
    }

    @Test
    fun `quality of Backstage Pass increases by one after each update before sellIn is 10`() {
        val expectedQualityValues = listOf(5, 6, 7, 8, 9, 10)
        val actualQualityValues = mutableListOf(expectedQualityValues.first())
        val safeInitialSellIn = 10 + expectedQualityValues.size + 1
        val item = Item(backstagePassName, safeInitialSellIn, expectedQualityValues.first())
        val app = GildedRose(arrayOf(item))

        while (actualQualityValues.size < expectedQualityValues.size) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualQualityValues.add(storedItem!!.quality)
        }

        Assertions.assertIterableEquals(expectedQualityValues, actualQualityValues)
    }

    @Test
    fun `quality of Backstage Pass increases by two when sellIn is between 10 and 6`() {
        val expectedQualityValues = listOf(5, 7, 9, 11, 13)
        val actualQualityValues = mutableListOf(expectedQualityValues.first())
        val initialSellIn = 10
        val item = Item(backstagePassName, initialSellIn, expectedQualityValues.first())
        val app = GildedRose(arrayOf(item))

        for (i in initialSellIn - 1 downTo 6) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualQualityValues.add(storedItem!!.quality)
        }

        Assertions.assertIterableEquals(expectedQualityValues, actualQualityValues)
    }

    @Test
    fun `quality of Backstage Pass increases by three when sellIn is between 5 and 0`() {
        val expectedQualityValues = listOf(5, 8, 11, 14, 17, 20)
        val actualQualityValues = mutableListOf(expectedQualityValues.first())
        val initialSellIn = 5
        val item = Item(backstagePassName, initialSellIn, expectedQualityValues.first())
        val app = GildedRose(arrayOf(item))

        for (i in initialSellIn - 1 downTo 0) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualQualityValues.add(storedItem!!.quality)
        }

        Assertions.assertIterableEquals(expectedQualityValues, actualQualityValues)
    }

    @Test
    fun `quality of Backstage Pass decreases to 0 when sellIn is under 0`() {
        val expectedQualityValues = listOf(5, 0, 0, 0)
        val actualQualityValues = mutableListOf(expectedQualityValues.first())
        val initialSellIn = 0
        val item = Item(backstagePassName, initialSellIn, expectedQualityValues.first())
        val app = GildedRose(arrayOf(item))

        while (actualQualityValues.size < expectedQualityValues.size) {
            app.updateQuality()
            val storedItem = app.items.find { it.name === item.name }
            actualQualityValues.add(storedItem!!.quality)
        }

        Assertions.assertIterableEquals(expectedQualityValues, actualQualityValues)
    }
}

/*
    Tests a añadir:
    - Quality no se puede inicializar en negativo
    - Quality no se puede inicializar en > 50
 */
