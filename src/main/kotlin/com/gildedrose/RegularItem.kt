package com.gildedrose

class RegularItem(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        updateSellIn()
        modifyQualityBy(baseQualityVariation)
    }
}
