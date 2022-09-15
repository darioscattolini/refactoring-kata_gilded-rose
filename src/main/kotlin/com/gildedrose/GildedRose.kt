package com.gildedrose

import kotlin.Double.Companion.POSITIVE_INFINITY

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (item in items) {
            if (item.name == "Sulfuras, Hand of Ragnaros") continue

            item.sellIn--

            val qualityVariationAmount = if (item.sellIn < 0) -2 else -1

            when (item.name) {
                "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePasses(item)
                "Aged Brie" -> modifyQualityBy(item, -qualityVariationAmount)
                else -> modifyQualityBy(item, qualityVariationAmount)
            }
        }
    }

    private fun modifyQualityBy(item: Item, amount: Int) {
        item.quality = when (item.quality + amount) {
            in 50..POSITIVE_INFINITY.toInt() -> 50
            in 0..49 -> item.quality + amount
            else -> 0
        }
    }

    private fun updateBackstagePasses(item: Item) {
        val qualityVariationAmount = when (item.sellIn) {
            in 11..POSITIVE_INFINITY.toInt() -> 1
            in 6..10 -> 2
            in 0..5 -> 3
            else -> -item.quality
        }

        modifyQualityBy(item, qualityVariationAmount)
    }
}

