package com.gildedrose

class GildedRose(items: Array<Item>) {
    val items = items.map { UpdatableItem.fromItem(it) }

    fun updateQuality() {
        for (item in items) {
            item.updateQuality()
            val qualityVariationAmount = getQualityVariationAmount(item)

            when (item) {
                is BackstagePasses -> {}
                is AgedBrie -> item.modifyQualityBy(-qualityVariationAmount)
                is Sulfuras -> {}
                is ConjuredItem -> {}
                else -> item.modifyQualityBy(qualityVariationAmount)
            }
        }
    }

    private fun getQualityVariationAmount(item: Item) = if (item.sellIn < 0) -2 else -1
}

