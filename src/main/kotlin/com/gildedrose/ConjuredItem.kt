package com.gildedrose

class ConjuredItem(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        sellIn--

        val qualityVariationAmount = if (sellIn < 0) -4 else -2
        modifyQualityBy(qualityVariationAmount)
    }
}
