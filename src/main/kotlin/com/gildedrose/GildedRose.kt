package com.gildedrose

import kotlin.Double.Companion.POSITIVE_INFINITY

class GildedRose(items: Array<Item>) {
    val items = items.map { UpdatableItem.fromItem(it) }
    private val maxQuality = 50
    private val minQuality = 0
    private val positiveInfinity = POSITIVE_INFINITY.toInt()

    fun updateQuality() {
        for (item in items) {
            item.updateQuality()
            val qualityVariationAmount = getQualityVariationAmount(item)

            when (item) {
                is BackstagePasses -> updateBackstagePasses(item)
                is AgedBrie -> modifyQualityBy(item, -qualityVariationAmount)
                is Sulfuras -> {}
                else -> modifyQualityBy(item, qualityVariationAmount)
            }
        }
    }

    private fun getQualityVariationAmount(item: Item): Int {
        var qualityVariationAmount = if (item.sellIn < 0) -2 else -1
        if (item.name.startsWith("Conjured", true)) qualityVariationAmount *= 2

        return qualityVariationAmount
    }

    private fun modifyQualityBy(item: Item, amount: Int) {
        item.quality = when (item.quality + amount) {
            in maxQuality..positiveInfinity -> maxQuality
            in minQuality until maxQuality -> item.quality + amount
            else -> minQuality
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

