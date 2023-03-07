package com.gildedrose

class ConjuredItem(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        updateSellIn()
        modifyQualityBy(baseQualityVariation * 2)
    }
}
