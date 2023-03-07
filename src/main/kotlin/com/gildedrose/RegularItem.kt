package com.gildedrose

class RegularItem(item: Item) : UpdatableItem(item) {
    override fun updateQuality() {
        sellIn--
        modifyQualityBy(baseQualityVariation)
    }
}
