package com.gildedrose

class ConjuredItem(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        sellIn--

        val qualityVariationAmount = baseQualityVariation * 2
        modifyQualityBy(qualityVariationAmount)
    }
}
