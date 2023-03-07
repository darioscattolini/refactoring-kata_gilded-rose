package com.gildedrose

import kotlin.Double.Companion.POSITIVE_INFINITY

class GildedRose(items: Array<Item>) {
    val items = items.map { UpdatableItem.fromItem(it) }

    fun updateQuality() {
        for (item in items) {
            item.updateQuality()
            val qualityVariationAmount = getQualityVariationAmount(item)

            when (item) {
                is BackstagePasses -> updateBackstagePasses(item)
                is AgedBrie -> item.modifyQualityBy(-qualityVariationAmount)
                is Sulfuras -> {}
                else -> item.modifyQualityBy(qualityVariationAmount)
            }
        }
    }

    private fun getQualityVariationAmount(item: Item): Int {
        var qualityVariationAmount = if (item.sellIn < 0) -2 else -1
        if (item.name.startsWith("Conjured", true)) qualityVariationAmount *= 2

        return qualityVariationAmount
    }

    private fun updateBackstagePasses(item: UpdatableItem) {
        val qualityVariationAmount = when (item.sellIn) {
            in 11..POSITIVE_INFINITY.toInt() -> 1
            in 6..10 -> 2
            in 0..5 -> 3
            else -> -item.quality
        }

        item.modifyQualityBy(qualityVariationAmount)
    }
}

