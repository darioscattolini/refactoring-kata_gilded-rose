package com.gildedrose

import kotlin.Double.Companion.POSITIVE_INFINITY

class GildedRose(var items: Array<Item>) {
    private fun decreaseQualityBy(item: Item, amount: Int) {
        item.quality = if (item.quality - amount >= 0) item.quality - amount else 0
    }

    private fun increaseQualityBy(item: Item, amount: Int) {
        item.quality = if (item.quality + amount <= 50) item.quality + amount else 50
    }

    fun updateQuality() {
        for (item in items) {
            if (item.name == "Sulfuras, Hand of Ragnaros") continue

            item.sellIn--

            var qualityVariationAmount = if (item.sellIn < 0) 2 else 1

            if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                qualityVariationAmount = when (item.sellIn) {
                    in 11..POSITIVE_INFINITY.toInt() -> 1
                    in 6..10 -> 2
                    in 0..5 -> 3
                    else -> item.quality
                }

                if (item.sellIn >= 0) {
                    increaseQualityBy(item, qualityVariationAmount)
                } else {
                    decreaseQualityBy(item, qualityVariationAmount)
                }
            } else if (item.name == "Aged Brie") {
                increaseQualityBy(item, qualityVariationAmount)
            } else {
                decreaseQualityBy(item, qualityVariationAmount)
            }
        }
    }

}

